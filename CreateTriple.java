package stanford_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CreateTriple {
	private String texte;
	private String asp;
	private ArrayList<String> liste;
	private String noun;
	private ArrayList<String> ls;
	private String type;
	private String senti;
	public CreateTriple() throws IOException, InterruptedException{
		
		Scanner fr=new Scanner(new FileReader("trial.txt"));
		while(fr.hasNext()){
		texte=fr.nextLine();
		asp=AspectMain.getnoun().get(1);
		noun =AspectMain.getnoun().get(0);
		//senti=AspectMain.getaspects();
		ls=new ArrayList<String>();
		liste=new ArrayList<String>();
		}					
				
}
	
public String getTexte(){
	return texte;
}
public String getAsp(){
	return asp;
}
public String getType() throws IOException {
	
	return noun;
	}
/*public String getSentis(){
	return senti;
}*/

	
}
