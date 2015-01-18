import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;



/**
 * @author Loris NORSIC
 * This plugin enables to convert a .rtf file in .txt file 
 * 
 *
 */


public class rtf {
    private File fileEntree;
    private File fileSortie;

    private String REGEX = Messages.getString("rtf.0");// //$NON-NLS-1$
    private String REGEX2 = Messages.getString("rtf.1"); //$NON-NLS-1$
    private String REGEX3 = Messages.getString("rtf.2"); //$NON-NLS-1$
    private String REGEX4 = Messages.getString("rtf.3"); //$NON-NLS-1$
    private String REGEX5 = Messages.getString("rtf.4"); //$NON-NLS-1$
    private String REGEX6 = Messages.getString("rtf.5"); //$NON-NLS-1$
    private String REPLACE = Messages.getString("rtf.6"); //$NON-NLS-1$
    private String REPLACE2 = Messages.getString("rtf.7"); //$NON-NLS-1$
    private String REPLACE3 = Messages.getString("rtf.8"); //$NON-NLS-1$

    public rtf(String in, String out) {
        this.fileEntree = new File(in);
        this.fileSortie = new File(out);

    }

    //replace line by line with the regular expression
    public void test() throws IOException {
    	
    	String airlines = FileUtils.readFileToString(fileEntree); //File is transformed in String
    	String text = airlines.replaceAll(this.REGEX, this.REPLACE2); //head of file is delete
    	String tab[] = text.split(Messages.getString("rtf.9")); //$NON-NLS-1$
    	
    	
    	FileUtils.deleteQuietly(fileSortie);//Delete the file with the same name
        for (int i = 0; i < tab.length; i++) { //REGEX used for each line of String tab[]
            String newline = tab[i].replaceAll(this.REGEX2, this.REPLACE);
            newline = newline.replaceAll(this.REGEX3, this.REPLACE2);
            newline = newline.replaceAll(this.REGEX4, this.REPLACE2);
            newline = newline.replaceAll(this.REGEX5, this.REPLACE3);
            newline = newline.replaceAll(this.REGEX6, this.REPLACE3);
            tab[i] = newline;
            FileUtils.write(fileSortie, tab[i], true);
        }
    }


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        rtf test = new rtf(Messages.getString("rtf.10"), Messages.getString("rtf.11")); //$NON-NLS-1$ //$NON-NLS-2$
        test.test();
    }
}
