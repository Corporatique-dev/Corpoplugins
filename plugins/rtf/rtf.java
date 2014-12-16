import java.io.*;


public class rtf {
	private File fileEntree;
	private File fileSortie;

	private String REGEX = "\\\\(par)\\W|\\\\(pard)";
	private String REGEX2 = "\\{[^\\;]+\\;\\}|\\\\[^ ]+[ ]+|\\{|\\}|[0-9a-fA-F]{127}|[0-9a-fA-F]{12}";
	private String REGEX3 = "^\\\\.*;\\\\|\\\\[^ ]+[\\w+\\d*]";
	private String REGEX4 = ".*\\;.*[a-zA-a]*\\;";
	private String REPLACE = "\n";
	private String REPLACE2 = "";

	public rtf(String in, String out){
		this.fileEntree = new File(in);
		this.fileSortie = new File(out);
		
	}
	
	//replace line by line with the regular expression
	public void test() throws IOException{
		FileWriter fw = new FileWriter(this.fileSortie);
		BufferedWriter bw = new BufferedWriter(fw);
		FileReader fr = new FileReader(this.fileEntree);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while((line=br.readLine())!=null){
			String newline = line.replaceAll(this.REGEX,this.REPLACE);
			newline = newline.replaceAll(this.REGEX2,this.REPLACE2);
			newline = newline.replaceAll(this.REGEX3, this.REPLACE2);
			newline = newline.replaceAll(this.REGEX4, this.REPLACE2);
			bw.write(newline);
		}
		br.close();
		bw.close();
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		rtf test = new rtf("buffalo96.rtf","test.rtf");
		test.test();
	}
}
