import java.io.*;


public class rtf {
	private File fileEntree;
	private File fileSortie;
	private String REGEX = "\\{[^\\;]+\\;\\}|\\\\[^ ]+[ ]+|\\{|\\}|[0-9a-fA-F]{127}|[0-9a-fA-F]{12}";
	private String REGEX2 = "^\\.*;\\";
	private String REPLACE = "";

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
			bw.write(newline);
		}
		br.close();
		bw.close();
	}
	
	public void test2() throws IOException{
		FileWriter fw2 = new FileWriter(this.fileSortie);
		BufferedWriter bw2 = new BufferedWriter(fw2);
		FileReader fr2 = new FileReader(this.fileEntree);
		BufferedReader br2 = new BufferedReader(fr2);
		String line = br2.readLine();
		while((line=br2.readLine())!=null){
			String newline = line.replaceAll(this.REGEX2,this.REPLACE);
			bw2.write(newline);
		}
		br2.close();
		bw2.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		rtf test = new rtf("buffalo96.rtf","test.rtf");
		test.test();
		rtf test2 = new rtf("test.rtf", "test2.rtf");
		test2.test2();
	}
}
