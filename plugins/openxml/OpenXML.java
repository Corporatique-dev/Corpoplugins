package plugins.openxml;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.zip.*;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import plugins.Corpoplugins;
import plugins.Pluginspecs;

@Pluginspecs(
        name = "OpenXML",
        version = "1.0.0",
        description = "Extracts text from .docx and .odt Files",
        author = "Maxime CHAPUIS",
        dependencies = "XML",
        extensions = {"docx","odt"})
@PluginImplementation
public class OpenXML implements Corpoplugins{

	private File in;
	private File out;
	private ZipFile z;
	
	private static final int BUF_SIZE = 1024;
 	private static final String CONTENT_ODT = Messages.getString("OpenXML.0");
	private static final String CONTENT_DOCX = Messages.getString("OpenXML.2");
	private static final String CONTENT_DOC = Messages.getString("OpenXML.3");
	private static final String CASE1 = Messages.getString("OpenXML.4");
	private static final String CASE2 = Messages.getString("OpenXML.5");
	private static String SYS_KEY_SEP = System.getProperty(Messages.getString("OpenXML.1"));
	
	@Override
	public void Load(File file_in, File file_out) {
        this.setFileIn(file_in);
        this.setFileOut(file_out);
        try {
			this.z = new ZipFile(this.in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
	public void processExtraction(String[] options) throws IOException {
		String file_path = this.z.getName();
		String ext = file_path.substring(file_path.lastIndexOf('.')+1).toLowerCase();//On récupère l'extension
	        if(Objects.equals(ext, CASE1)) {
	            this.extract(CONTENT_ODT);
	        } else if(Objects.equals(ext, CASE2)) {
 	           this.extract(CONTENT_DOCX);
        	} else {
        	    System.out.println(Messages.getString("OpenXML.6"));
	        }
	}
	
	private void extract(String content) throws IOException{
		BufferedInputStream z_in = new BufferedInputStream(z.getInputStream((z.getEntry(content))),BUF_SIZE);
		String out = this.z.getName() +Messages.getString("OpenXML.7"); //$NON-NLS-1$
		FileOutputStream fos = new FileOutputStream(out);
		BufferedOutputStream bos = new BufferedOutputStream(fos,BUF_SIZE);
		//extraction du fichier xml
		byte[] b = new byte[BUF_SIZE];
		int nread = z_in.read(b, 0, BUF_SIZE);
		while(nread != -1){
			bos.write(b, 0, nread);;
			nread = z_in.read(b, 0, BUF_SIZE);
		}
		bos.close();
		z_in.close();
		File f = new File(out);
		//Utilisation du plugin xml pour traité le xml extrait
		Corpoplugins xml = Execute.thisPlugin("xml");
		xml.Load(f, this.out);
		xml.processExtraction(null);
		
		f.delete();
			
		}
	
	public void setFileIn(File file_in) {
		this.in = file_in;
		
	}

	public void setFileOut(File file_out) {
		this.out = file_out;
		
	}			
}

