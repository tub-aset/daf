package de.jpwinkler.daf.db;

import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsPackage;
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
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class FileDatabaseInterface implements DatabaseInterface {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        DoorsPackage.eINSTANCE.eClass();
    }

    private DoorsFolder databaseRoot;
    private DatabasePath<FileDatabaseInterface> databasePath;

    public FileDatabaseInterface(DatabasePath<FileDatabaseInterface> databasePath, OpenFlag openFlag) throws IOException {
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
                    folders.get(file.getParent().toAbsolutePath().toString()).getChildren().add(ModuleCSV.read(file.toFile()));
                }

                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                DoorsFolder folder = DoorsModelUtil.createFolder(folders.get(dir.getParent().toAbsolutePath().toString()), FilenameUtils.getBaseName(dir.toFile().getAbsolutePath()));
                if (folder.getParent() == null) {
                    databaseRoot = folder;
                }

                folder.getAttributes().putAll(ModuleCSV.readMetaData(dir.resolve("__folder__.mmd").toFile()));
                folders.put(dir.toAbsolutePath().toString(), folder);

                return super.preVisitDirectory(dir, attrs);
            }

        });
    }

    @Override
    public final void flush() throws IOException {
        FileUtils.deleteDirectory(new File(databasePath.getDatabasePath()));
        databaseRoot.accept(new DoorsTreeNodeVisitor<>(DoorsFolder.class) {
            @Override
            protected void visitPostTraverse(DoorsFolder f) {
                try {
                    Path modulePath = Paths.get(Paths.get(databasePath.getDatabasePath()).getParent().toAbsolutePath().toString(), f.getFullNameSegments().toArray(new String[0]));
                    Files.createDirectories(modulePath);

                    ModuleCSV.writeMetaData(modulePath.resolve("__folder__.mmd").toFile(), f.getAttributes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        databaseRoot.accept(new DoorsTreeNodeVisitor<>(DoorsModule.class) {
            @Override
            protected void visitPostTraverse(DoorsModule m) {
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
    public DatabasePath<FileDatabaseInterface> getPath() {
        return databasePath;
    }

    @Override
    public DoorsFolder getDatabaseRoot() {
        return databaseRoot;
    }

    @Override
    public DoorsModule importModule(DoorsModule newModule) {
        DoorsTreeNode dbParent = this.ensureDatabasePath(newModule.getParent());
        DoorsModule dbModule = (DoorsModule) dbParent.getChild(newModule.getName());

        if (dbModule != null) {
            dbModule.setParent(null);
        }

        return DoorsModelUtil.createCopy(newModule, dbParent.getParent());
    }

    private DoorsTreeNode ensureDatabasePath(final DoorsTreeNode path) {
        return ensureDatabasePath(databaseRoot, path.getFullNameSegments());
    }

    private static DoorsTreeNode ensureDatabasePath(final DoorsTreeNode parent, final List<String> path) {
        if (path.size() > 0) {
            DoorsTreeNode node = parent.getChild(path.get(0));
            if (node == null) {
                node = DoorsModelUtil.createFolder(parent, path.get(0));
            }
            if (path.size() > 1) {
                return ensureDatabasePath(parent.getChild(path.get(0)), path.subList(1, path.size()));
            } else {
                return node;
            }
        } else {
            return null;
        }
    }
}
