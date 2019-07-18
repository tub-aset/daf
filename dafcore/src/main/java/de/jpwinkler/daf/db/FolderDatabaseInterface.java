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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FolderDatabaseInterface implements DatabaseInterface {

    private static final Logger LOG = Logger.getLogger(FolderDatabaseInterface.class.getName());

    private DoorsFolder databaseRoot;
    private DatabasePath databasePath;
    private final DatabaseFactory factory = new EmfDatabaseFactory();

    public FolderDatabaseInterface(DatabasePath databasePath, OpenFlag openFlag) throws IOException {
        if (!databasePath.getPath().isEmpty()) {
            throw new IllegalArgumentException("databasePath must not have a path segment here");
        }

        this.databasePath = databasePath;
        File databaseFile = new File(databasePath.getDatabasePath());

        if (openFlag == OpenFlag.CREATE_IF_INEXISTENT && !databaseFile.isDirectory()) {
            Files.createDirectories(databaseFile.toPath());
        } else if (openFlag == OpenFlag.ERASE_IF_EXISTS && databaseFile.exists()) {
            FileUtils.deleteDirectory(databaseFile);
            Files.createDirectories(databaseFile.toPath());
            new File(databaseFile, "__folder__.mmd").createNewFile();
        } else if (openFlag == OpenFlag.OPEN_ONLY && !databaseFile.isDirectory()) {
            throw new FileNotFoundException(databaseFile.getAbsolutePath());
        }

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
                    databaseRoot = folder;
                }

                try {
                    folder.getAttributes().putAll(ModuleCSV.readMetaData(factory, dir.resolve("__folder__.mmd").toFile()));
                    folders.put(dir.toAbsolutePath().toString(), folder);
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, "Failed reading folder metadata: " + dir.resolve("__folder__.mmd").toAbsolutePath().toString(), ex);
                    return FileVisitResult.SKIP_SUBTREE;
                }

                return super.preVisitDirectory(dir, attrs);
            }

        });
    }

    @Override
    public final void flush() throws IOException {
        FileUtils.deleteDirectory(new File(databasePath.getDatabasePath()));
        this.databaseRoot.setName(new File(databasePath.getDatabasePath()).getName());
        databaseRoot.accept(new DoorsTreeNodeVisitor<DoorsFolder, Void>(DoorsFolder.class) {
            @Override
            public void visitPostTraverse(DoorsFolder f) {
                try {
                    Path modulePath = Paths.get(Paths.get(databasePath.getDatabasePath()).getParent().toAbsolutePath().toString(), f.getFullNameSegments().toArray(new String[0]));
                    Files.createDirectories(modulePath);

                    ModuleCSV.writeMetaData(modulePath.resolve("__folder__.mmd").toFile(), f.getAttributes());
                    if(f.isProject()) {
                        Files.createFile(modulePath.resolve("__project__"));
                    } else {
                        Files.deleteIfExists(modulePath.resolve("__project__"));
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        databaseRoot.accept(new DoorsTreeNodeVisitor<DoorsModule, Void>(DoorsModule.class) {
            @Override
            public void visitPostTraverse(DoorsModule m) {
                try {
                    Path folderPath = Paths.get(Paths.get(databasePath.getDatabasePath()).getParent().toAbsolutePath().toString(), m.getParent().getFullNameSegments().toArray(new String[0]));
                    ModuleCSV.write(
                            folderPath.resolve(m.getName() + ".csv").toFile(),
                            folderPath.resolve(m.getName() + ".mmd").toFile(), m);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public DatabasePath getPath() {
        return databasePath;
    }

    @Override
    public DoorsFolder getDatabaseRoot() {
        return databaseRoot;
    }

    @Override
    public DatabaseFactory getFactory() {
        return factory;
    }
}
