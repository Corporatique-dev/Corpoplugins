Corporatique_plugins
====================

List of plugins of Corporatique

## Plugin API
Corporatique is using the **Jspf** framework to add, delete and update Corpoplugins.
Therefore, there are some rules to follow when you create a plugin for it.

The plugin need to :
1. to implement the `Corpoplugins` interface.
2. to have the `@Pluginspecs` annotation
3. to have the `@PluginImplementation` annotation
4. to be under `plugins.plugin-name` package ex `plugins.doc`



You can see the entire code [here](#Example)



### Example
First of all, this is what it looks like, we will explain all the different parts later :
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