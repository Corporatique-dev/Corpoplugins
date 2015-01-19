Corporatique plugins
=======

List of plugins of Corporatique

## Plugin API

Corporatique framework uses only two methods to load the files and execute the Corpoplugin :

  * Will initialize the plugin with an empty constructor (ex:`Plugin()`)
  * `public void Load(File file_in, File file_out)` which will load files to extract and the extraction destination. The input and output are setted by the framework to avoid any problems with a plugin on extraction.
  * `public void processExtraction(String[] options)` will be executed right after `Load(File file_in, File file_out)` to process the extraction. If specified, it will send additionals options in a `processExtraction()`, these are the options relative to the plugin.

Corporatique is using the **[Jspf]** framework to add, delete and update Corpoplugins.

Therefore, there are some rules to follow when you create a plugin for it.

You can see an example of a plugin [here](#example-of-doc-plugin)
#### What needs to be present :
##### Implement the `Corpoplugins` interface.
That's one of the most important things, if the principal class of the plugins doesn't implements it, the software will not recognise it.

##### Have the `@Pluginspecs` annotation
That's the second most important thing. it allows the plugin to identify himself to Corporatique/PluginManager, it contains

 * `String name` which is the name of the plugin, each plugin need to have a unique name
 * `String version` allows you to have a version control
 * `String description` contains a short description
 * `String author` for the rights
 * `String [ ] dependencies`  if the plugin need others plugins to be installed to work
 * `String [ ] extensions`  different formats which can be processed by the plugin

#####`@PluginImplementation` annotation
Part of the **[Jspf]** framework, need to be present to have the plugin recognised by PluginManager

##### Extraction options in `processExtraction(String [] options)` [Optional]
This is not required but, if you want to add some option (example : ignore numerated lists) to you plugin.

##### Be under `plugins.plugin-name` package; example `package plugins.doc` [Optional]
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
        dependencies = "",
        extensions = "doc")
@PluginImplementation
public class Doc implements Corpoplugins {

    protected static int BUF_SIZE = 4096;
    private File in;
    private File out;

    public Doc(){}

    public void Load(File file_in, File file_out) {
        this.setFileIn(file_in);
        this.setFileOut(file_out);
    }

    public void processExtraction(String[] options){
        {...}
    }

    public void setFileIn(File file_in) {
        this.in = file_in;
    }

    public void setFileOut(File file_out) {
        this.out = file_out;
    }
}
```
[jspf]:https://code.google.com/p/jspf/
