Corporatique_plugins
====================

List of plugins of Corporatique

## Plugin API
Corporatique is using the **Jspf** framework to add, delete and update Corpoplugins.
Therefore, there are some rules to follow when you create a plugin for it.

You can see an example of a plugin [here](#example)

##### Implement the `Corpoplugins` interface.
That's one of the most important things, if the principal class of the plugins doesn't implements it, the software will not recognise it.

##### Have the `@Pluginspecs` annotation
That's the second most important thing. it allows the plugin to identify himself to Corporatique/PluginManager, it contains

 * `name` which is the name of the plugin, each plugin need to have a unique name
 * `version` allows you to have a version control
 * `description` contains a short description
 * `author` for the rights
 * `dependencies` String[] if the plugin need others plugins to be installed to work
 * `extensions` String[] differents formats which can be processed by the plugin

#####`@PluginImplementation` annotation
Part of the **[Jspf](https://code.google.com/p/jspf/)** framework, need to be present to have the plugin recognised by PluginManager


##### Be under `plugins.plugin-name` package example `package plugins.doc` [Optional]
This is not required, but recommended for cohesion reasons


## Example of doc plugin
```java
package plugins.doc;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import plugins.Corpoplugins;
import plugins.Pluginspecs;

import java.io.*

@Pluginspecs(
        name = "Doc",
        version = "1.0.0",
        description = "Extracts text from .doc Files",
        author = "Maxime CHAPUIS",
        dependencies = "none",
        extensions = "none")
@PluginImplementation
public class Doc implements Corpoplugins {

    protected static int BUF_SIZE = 4096;
    private File in;
    private File out;

    public Doc(){}

    @Override
    public void Load(File file_in, File file_out) {
        this.in = new File(in.getAbsolutePath());
        this.out = new File(out.getAbsolutePath());
    }

    @Override
    public void processExtraction(String[] options) throws IOException {
        {...}
    }

    @Override
    public File getFileIn() {
        // TODO Auto-generated method stub
        return this.in;
    }

    @Override
    public File getFileOut() {
        return this.out;
    }

    @Override
    public void setFileIn(File file_in) {
        this.in = file_in;

    }

    @Override
    public void setFileOut(File file_out) {
        this.out = file_out;
    }
}
```