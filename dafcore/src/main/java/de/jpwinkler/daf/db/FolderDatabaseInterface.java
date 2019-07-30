package de.jpwinkler.daf.db;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.mutable.MutableObject;

public class FolderDatabaseInterface implements DatabaseInterface {

    public static final String DOORS_DB_MMD = "__doors_db__.mmd";

    private static final Logger LOG = Logger.getLogger(FolderDatabaseInterface.class.getName());

    private CompletableFuture<DoorsFolder> databaseRoot;
    private DatabasePath databasePath;
    private final DatabaseFactory factory = new EmfDatabaseFactory();

    public FolderDatabaseInterface(BackgroundTaskExecutor executor, DatabasePath databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;
        this.databaseRoot = executor.runBackgroundTask("Loading folder database", i -> {
            try {
                File dbFile = new File(databasePath.getDatabasePath(), DOORS_DB_MMD);
                if (!dbFile.isFile()) {
                    return factory.createFolder(null, "Empty folder database", false);
                }

                File databaseFile = new File(databasePath.getDatabasePath(), ModuleCSV.readMetaData(dbFile).get("ROOT"));

                if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !databaseFile.isDirectory()) {
                    Files.createDirectories(databaseFile.toPath());
                } else if (openFlag == OpenFlag.ERASE_IF_EXISTS && databaseFile.exists()) {
                    FileUtils.deleteDirectory(databaseFile);
                    Files.createDirectories(databaseFile.toPath());
                } else if (openFlag == OpenFlag.OPEN_ONLY && !databaseFile.isDirectory()) {
                    throw new FileNotFoundException(databaseFile.getAbsolutePath());
                }

                MutableObject<DoorsFolder> root = new MutableObject<>();
                Files.walkFileTree(databaseFile.toPath(), new SimpleFileVisitor<Path>() {
                    private final HashMap<String, DoorsFolder> folders = new HashMap<>();

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if ("csv".equals(FilenameUtils.getExtension(file.toString()))) {
                            try {
                                folders.get(file.getParent().toAbsolutePath().toString()).getChildren().add(ModuleCSV.read(factory, file.toFile()));
                            } catch (IOException ex) {
                                LOG.log(Level.SEVERE, "Failed reading module: " + file.toAbsolutePath().toString(), ex);
                            }
                        }

                        return super.visitFile(file, attrs);
                    }

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        DoorsFolder folder = factory.createFolder(
                                folders.get(dir.getParent().toAbsolutePath().toString()),
                                FilenameUtils.getBaseName(dir.toFile().getAbsolutePath()),
                                Files.exists(dir.resolve("__project__")));
                        if (folder.getParent() == null) {
                            root.setValue(folder);
                        }

                        try {
                            folder.getAttributes().putAll(ModuleCSV.readMetaData(dir.resolve("__folder__.mmd").toFile()));
                            folders.put(dir.toAbsolutePath().toString(), folder);
                        } catch (IOException ex) {
                            LOG.log(Level.SEVERE, "Failed reading folder metadata: " + dir.resolve("__folder__.mmd").toAbsolutePath().toString(), ex);
                            return FileVisitResult.SKIP_SUBTREE;
                        }

                        return super.preVisitDirectory(dir, attrs);
                    }

                });

                if (root.getValue() == null) {
                    throw new IOException("No root node found");
                }
                return root.getValue();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public final CompletableFuture<Void> flushAsync() {
        return this.databaseRoot.thenAccept(root -> {
            try {
                File dbFile = new File(databasePath.getDatabasePath(), DOORS_DB_MMD);
                dbFile.createNewFile();
                ModuleCSV.writeMetaData(dbFile, Collections.singletonMap("ROOT", root.getName()));

                File databaseRootDir = new File(new File(databasePath.getDatabasePath()), root.getName());
                FileUtils.deleteDirectory(databaseRootDir);
                root.accept(new DoorsTreeNodeVisitor<DoorsFolder, Void>(DoorsFolder.class) {
                    @Override
                    public void visitPostTraverse(DoorsFolder f) {
                        try {
                            Path modulePath = Paths.get(databaseRootDir.getAbsolutePath(), f.getFullNameSegments().toArray(new String[0]));
                            Files.createDirectories(modulePath);

                            ModuleCSV.writeMetaData(modulePath.resolve("__folder__.mmd").toFile(), f.getAttributes());
                            if (f.isProject()) {
                                modulePath.resolve("__project__").toFile().createNewFile();
                            } else {
                                Files.deleteIfExists(modulePath.resolve("__project__"));
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                root.accept(new DoorsTreeNodeVisitor<DoorsModule, Void>(DoorsModule.class) {
                    @Override
                    public void visitPostTraverse(DoorsModule m) {
                        try {
                            Path folderPath = Paths.get(databaseRootDir.getAbsolutePath(), m.getParent().getFullNameSegments().toArray(new String[0]));
                            ModuleCSV.write(
                                    folderPath.resolve(m.getName() + ".csv").toFile(),
                                    folderPath.resolve(m.getName() + ".mmd").toFile(), m);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public CompletableFuture<DoorsFolder> getDatabaseRootAsync() {
        return databaseRoot;
    }

    @Override
    public Class<? extends DoorsTreeNode> getDatabaseRootClass() {
        return DoorsFolder.class;
    }

    @Override
    public DatabaseFactory getFactory() {
        return factory;
    }
}
