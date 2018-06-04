package stanford_1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

public class AspectMain {
	static NounsGenerator ng;
	static Aspect aspect;
	public static ArrayList<String> getnoun() throws IOException{
		ng=new NounsGenerator("preprocessedFile","nounsFile");
		ng.generateAspects();
		return ng.getNounsListString();
	}
	
	
	 public static String getaspects() throws IOException, InterruptedException{
		   String a = "/home/emily/workspace/Extraction_2/trial.txt";
	      Preprocess preprocess = new Preprocess("trial.txt", "preprocessedFile");
			AspectsGenerator ag = new AspectsGenerator(getnoun(), 15);
			System.out.println(ag.getSuggestedAspects());

			ArrayList<String> finalAspects = new ArrayList<>();
			finalAspects.add("camera");
			finalAspects.add("colour");
			finalAspects.add("color");
			finalAspects.add("battery");
			finalAspects.add("screen");
			finalAspects.add("processor");
			finalAspects.add("price");
			finalAspects.add("apps");
			finalAspects.add("sound");
			finalAspects.add("radio");
			ag.finalizeAspects(finalAspects);

			// checking and debugging
			ArrayList<Aspect> ans;
			ans = ag.getAspects();

			for (Aspect aspect : ans) {
				System.out.println(aspect.getAspectName());
			}
			preprocess.finalPreprocess("preprocessedFile", "opinionatedReviews", ans);
			
					Aspect.setReviewFile("opinionatedReviews");
			ExecutorService pool = Executors.newFixedThreadPool(ans.size());
			for (int i = 0; i < ans.size(); i++) {
				pool.submit(ans.get(i));
			}
			
			pool.submit(ans.get(0));
			pool.shutdown();
			
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			String s="";
			for (Aspect aspect: ans) {
				s+=aspect.getOpinionWords();
				
			}
			return s;
	}

	
	public static void emi(String[] args) throws IOException, InterruptedException {

		/*
		 * 1. FIRST STEP INVOLVES PREPROCESSING, i.e, REMOVAL OF ERRANT SYMBOLS,
		 * STOP WORD REMOVAL, SENTENCE SEGMENTATION, REMOVAL OF UNNECESSARY
		 * SPACES
		 */
	   String a = "/home/emily/workspace/Extraction_2/trial.txt";
      Preprocess preprocess = new Preprocess("trial.txt", "preprocessedFile");
      //CreateTriple cr = new CreateTriple();
		/*Scanner fr=new Scanner(new FileReader("trial.txt"));
		
		while(fr.hasNext()){
			//String ligne = fr.nextLine();}
		Preprocess preprocess = new Preprocess("trial.txt", "preprocessedFile");
	    PosTagger ptdemo = new PosTagger();
		String taggedString = ptdemo.tagString(fr.nextLine());
		System.out.println("tagged:\n" + taggedString);
		String simpleString = ptdemo.convertToSimpleTags(taggedString);
		System.out.println("simple-tagged:\n" + simpleString);*/
		/*
		 * 2. THE SECOND STEP INVOLVES GENERATION OF NOUNS, i.e, GENERATE ALL
		 * POSSIBLE NOUN FORMS, PARSE THE PROCESSED REVIEWS, TO GENERATE NOUNS
		 * THE NOUNS ARE RECORDED AS "nounsFile" AND "parsedFile" CAN BE USED
		 * FOR DEBUGGING ONE MAJOR IMPROVEMENT-->IF THE "parsedFile" IS ALREADY
		 * PRESENT THEN PARSING WILL NOT TAKE PLACE AGAIN..THE PREVIOUS NOUN
		 * FILE WILL BE USED TO RETURN THE NOUNS
		 */
     
		 ng = new NounsGenerator("preprocessedFile","nounsFile");
		ng.generateAspects();
//         String txt=ng.getNounsListString().toString();
//         System.out.println(txt);
		System.out.println(ng.getNounsListString()+"\n");

		/*
		 * 3. THE THIRD STEP INVOLVES GENERATION OF ASPECTS FROM THE NOUNS BASED
		 * ON THE FREQUENCY PARAMETER SPECIAL "Aspect" OBJECT IS CREATED TO
		 * HANDLE EACH ASPECT.......ASPECTS NEED TO BE FINALISED AND COMITTED TO
		 * A FILE "finalizedAspects"
		 */
		
		AspectsGenerator ag = new AspectsGenerator(ng.getNounsListString(), 15);
		System.out.println(ag.getSuggestedAspects());
		System.out.println("\n **");

		ArrayList<String> finalAspects = new ArrayList<>();
		finalAspects.add("camera");
		finalAspects.add("colour");
		finalAspects.add("color");
		finalAspects.add("battery");
		finalAspects.add("screen");
		finalAspects.add("processor");
		finalAspects.add("price");
		finalAspects.add("apps");
		finalAspects.add("sound");
		finalAspects.add("radio");
		ag.finalizeAspects(finalAspects);

		// checking and debugging
		ArrayList<Aspect> ans;
		ans = ag.getAspects();

		for (Aspect aspect : ans) {
			System.out.println(aspect.getAspectName());
		}

		/*
		 * 4. THE FOUTRH STEP INVOLVES FINAL PROCESSING OF THE
		 * "preprocessedFile" HERE ONLY THE LINES WHICH WILL HAVE THE ASPECTS
		 * MENTIONED IN THEM WILL BE INCLUDED FOR FURTHER EXTRACTION PROCEDURE.
		 * NEW FILE WILL BE CREATED FOR SUCH REVIEWS , "opinionatedReviews"
		 */
		
		preprocess.finalPreprocess("preprocessedFile", "opinionatedReviews", ans);
		
		/*
		 * 5. "THE" STEP FIVE
		 */
		//IMPORTANT IMPORTANT
		Aspect.setReviewFile("opinionatedReviews");
		//TRIAL
		//Thread t1 = new Thread(ans.get(0));
		//Thread t2 = new Thread(ans.get(1));
		//t1.start();
		//t2.start();
		
		
		//EXE POOL
		ExecutorService pool = Executors.newFixedThreadPool(ans.size());
		for (int i = 0; i < ans.size(); i++) {
			pool.submit(ans.get(i));
		}
		
		pool.submit(ans.get(0));
		pool.shutdown();
		
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		
		
		//Basic Thread Method - 6:10
		/*ArrayList<Thread> execute = new ArrayList<>();
		for (Aspect aspect: ans) {
			execute.add(new Thread(aspect));
		}
		
		for (Thread t: execute) {
			t.start();
		}
		
		for (Thread t: execute) {
			t.join();
		}*/
		
	
		
		/*for (Aspect aspect: ans) {
			System.out.println(aspect.getAspectName());
			System.out.println("----------------------------------");
			aspect.generateAspects();
		}*/
		/*DBpediaConnector connector1 = new DBpediaConnector(new URL("http://dbpedia.org/data/Smartphone.rdf"));
        String b=connector1.toString();*/
        //System.out.println(b); 
		  // DBpediaConnector connector = new DBpediaConnector(new URL("http://dbpedia.org/page/Smartphone"));
		for (Aspect aspect: ans) {
			System.out.println(aspect.getAspectName());
			System.out.println("----------------------------------\n");
			System.out.println(aspect.getOpinionWords());
			System.out.println(aspect.getAspect());
//			System.out.println(aspect.getSenti());
			System.out.println(aspect.getScore()+"now");
			
			
			//OpinionWord a = new OpinionWord("processor","good","a",true);
			//System.out.println(a.value);
		}
		}
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.print(AspectMain.getaspects());
	}
	}


