package plugins;

import net.xeoh.plugins.base.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Interface which <b>must</b> be implemented by all Corpoplugins.
 * The framework uses this interface to interface to interact with Corpoplugins. 
 * 
 * @author Fati CHEN
 * @version 1.0.0
 */
@Pluginspecs(
        name = "Plugin",
        version = "1.0.0",
        description = "Interface, every plugin should implement it",
        author = "Fati CHEN",
        dependencies = "none",
        extensions = "none")
public interface Corpoplugins extends Plugin {
    File file_in = null;
    File file_out = null;

    /**
     * Loading the selected file in the plugin and sets the output file
     * (security measures)
     *
     * @param file_in File to load
     */
    public void Load(File file_in, File file_out);

    /**
     * Process the text extraction to file_out with options if necessary.
     * The options must be filtered by the Corpoplugins
     *
     * @param options eventuals options of the plugin
     */
    public void processExtraction(String[] options) throws IOException;
}
