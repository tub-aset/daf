# DOORS Access Framework Overview

The DOORS Access Framework (DAF) provides simple access to existing IBM DOORS databases and simple editing and snapshotting capabilites. In addition, it is extensible with plugins to allow more complex calculations on the data (i.e. NLP, ML, etc.). The project is split into three components:

* dafcore: The central database access and management library. Can be used to develop one's own GUI, or CLI applications.
* dafgui: An extensible JavaFX-based GUI developped on top of dafcore.
* dafplugin: An example plugin showing how to extend dafgui.

## dafcore

dafcore revolves around a domain model described in eCore/EMF. The various database access classes (defined in `de.jpwinkler.daf.db`) export an object tree that must conform to the interfaces defined by this model, either by implementing it on their own or using the one automatically generated from the model. The following database access classes are available:

* DoorsApplicationDatabaseInterface (based on `de.jpwinkler.daf.bridge`): Provides read-only access to a locally installed DOORS application.
* FolderDatabaseInterface: Provides read/write access to a local directory structure containing `*.csv` and `*.mmd` files.
* RawFileDatabaseInterface: Provides read/write access to a single module in `*.csv`/`*.mmd` format.
* XmiDatabaseInterface: Provides read/write access to an XMI serialisation of the eCore model.

Based on these object graphs, two filter languages have been implemented (in `de.jpwinkler.daf.filter.*`) for objects and folders/modules.

## dafgui

This is a straightforward UI to explore and analyse DOORS datasets. Just try it out ;) It can be extended by plugins, such as the example plugin provided in dafplugin. These plugins either operate globally on the application (providing new views on the database, new database classes, global menu entries), or locally on a given view of a databases (providing local menu entries, local database panes and other extensions specifically tailored to a given view).

## dafplugin

An example plugin providing simple dummies to showcase the dafgui's plugin functionality.

# Compiling and running

The following should work out-of-the-box, no prerequisites necessary. All eCore/EMF/ant code generation will run automatically.

0. Install maven3
1. `cd dafcore && mvn clean install && cd ..`
2. `cd dafgui && mvn clean install && cd ..`
3. `cd dafplugin && mvn clean install && cd ..`
4. `java -jar dafgui/target/dafgui-1.0.0-SNAPSHOT-jar-with-dependencies.jar`

Important note: The dafgui JAR is platform-specific. If compiled on $X, it will only run on $X. This is due to the way JavaFX is packaged. The dafplugin jars themselves are platform-independent (as long as you do not add platform-specific calls) and can easily be installed via the `File` menu in dafgui.

# Contributing

To contribute, please create a new branch, apply your changes and push it to this repository. Then, create a merge request. If the code passes the review, it will be merged into master.

This project uses the netbeans default Java formatting. Please open any source file you edited in NetBeans with a clean formatting config, and click the menu item Source -> Format to achieve a correct code layout. We have looked into providing a .editorconfig file, but so far the work required has outweighed the benefits. If you feel like writing one, go ahead!
