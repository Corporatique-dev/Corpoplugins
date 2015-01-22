package plugins.pdf;

import java.io.File;
import java.io.IOException;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import plugins.Corpoplugins;
import plugins.Pluginspecs;

@Pluginspecs(
    name = "pdf",
    description = "Extracts text from pdf",
    version = "1.0.0",
    author = "Fati CHEN",
    dependencies = "",
    extensions = "pdf"
)
@PluginImplementation
public class Pdf implements Corpoplugins {
    private File file_in;
    private File file_out;

    public void Load(File file_in, File file_out) {
        this.setFile_in(file_in);
        this.setFile_out(file_out);
    }

    public void processExtraction(String[] options) throws IOException {
        PDDocument pdf = PDDocument.load(this.file_in);
        PDFTextStripper stripper = new PDFTextStripper();

        for(int i = 0; i < options.length-1; ++i) {
            if(options[i] == "startpage") {
                stripper.setStartPage(Integer.parseInt(options[i + 1]));
            }

            if(options[i] == "endpage") {
                stripper.setEndPage(Integer.parseInt(options[i + 1]));
            }
        }

        FileUtils.write(this.file_out, stripper.getText(pdf));
    }

    private void setFile_in(File file_in) {
        this.file_in = file_in;
    }

    private void setFile_out(File file_out) {
        this.file_out = file_out;
    }
}
