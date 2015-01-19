package plugins.doc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import plugins.Corpoplugins;
import plugins.Pluginspecs;

@Pluginspecs(
        name = "DOC",
        version = "1.0.0",
        description = "Extracts text from .doc Files",
        author = "Maxime CHAPUIS",
        dependencies = "",
        extensions = "doc")
@PluginImplementation

public class DOC implements Corpoplugins {

	private static int BUF_SIZE = 4096;
	private File in;
	private File out;
	
	public DOC(){}

	@Override
	public void Load(File file_in, File file_out) {
        this.setFileIn(file_in);
        this.setFileOut(file_out);
    }
	
	@Override
	public void processExtraction(String[] options) throws IOException {
		FileReader fis = new FileReader(this.in);
		BufferedReader br = new BufferedReader(fis,BUF_SIZE);   //Le buffer correspondant au fichier d'entré (le .doc)
		FileWriter fw = new FileWriter(this.out);
		BufferedWriter bw = new BufferedWriter(fw);   //Le buffer correspondant au fichier de sortie (le .txt)
		int nread;
		char[] chars = new char[BUF_SIZE];   //Le tableau de caractères dans lequel sont rangé après la lecture
		boolean text = false;   //vrai si le début du texte a été détécté
		boolean endOfText = false;   //vrai si la fin du texte a été détécté
		boolean header = true;   //faux si la fin du header a été détécté
		boolean firstLine = false;   //faux tant que la première ligne du texte n'a pas été écrite.
		while((nread = br.read(chars, 0, BUF_SIZE)) > 0){   //Tant qu'il y a des caractères à lire
			StringBuilder strb = new StringBuilder(); //Le StringBuilder correspondant au texte (les données textuelles qui seront dans le .txt)
			StringBuilder strb_header = new StringBuilder(); //Le StringBuilder correspondant au header, il servira à retrouver la première ligne du texte.
			for(int i = 0; i < nread;i++){ //Parcours du tableau chars
				if(!text && chars[i]==13){ //Recherche du premier caractère 'CR' (13 en int) (CR correspont à un retour chariot).
				//A cause de cela la première ligne est sauté, elle sera grâce au strb_header.
					text = true;
				}
				if(text && !endOfText && chars[i]==3){ //Recherche du premier END OF TEXT après le début du texte (3 en int) pour savoir quand s'arrête le texte. 
					endOfText=true;
				}
				if(text && !endOfText){ //Si le texte a commencé et que la fin n'a pas encore été trouvée,
					header = false;
					if(!Character.isISOControl(chars[i]) || Character.isWhitespace(chars[i])){ 
							//le caractère est ajouté à strb si ce n'est pas un caractère de controle
							strb.append(chars[i]);
					}	
				}
				if(header){ //Si la fin du header n'a pas été trouvée,
					//le caractère est ajouté à strb_header
					strb_header.append(chars[i]);
				}
			} 
			//Fin de la boucle de traitement du tableau chars
			
			if(!header && !firstLine){ //Si le header est terminé et que la première ligne n'a pas été écrite
				String head = strb_header.toString();
				int n = head.lastIndexOf('\0'); //Recherche du dernier nul du header, après celui ci, c'est la première ligne de texte.
				String firstL = head.substring(n+1); //+1 car on ne veut pas le nul
				bw.write(firstL); //Ecriture la première ligne dans le fichier txt
				firstLine=true;
			}
			bw.write(strb.toString()); //Ecriture des données textuelles dans le fichier txt
		}
		
		br.close();
		bw.close();	
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

