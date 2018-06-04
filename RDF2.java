package stanford_1;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
public class RDF2 {
	Aspect asp;
	static String texteURI="http://texte/";
	static String relationURI1="http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
	static String  relationURI2="http://www.example.org/tn#";
	
public static void main(String [] args) throws IOException, InterruptedException{
	
	CreateTriple cr=new CreateTriple();
	// Create an empty Model
	Model model = ModelFactory.createDefaultModel();
	 
	// Create a Resource
	Resource texte = model.createResource(texteURI+"/"+cr.getTexte());
    Resource aspect= model.createResource(texteURI+"/"+cr.getAsp());
	//Resource sentiment = model.createResource(texteURI+"/"+cr.getSentis());
	Resource type = model.createResource(texteURI+"/"+cr.getType());
	
	// Can also create properties directly . . .
	Property has= model.createProperty(relationURI1 , "has");
	Property prop = model.createProperty(relationURI2 , "prop");	
	//statement
	/*Statement stmt1 = model.createStatement(texte ,  has   , type);
	Statement stmt2 = model.createStatement(texte  , has   , aspect);
	Statement stmt3 = model.createStatement(aspect  , prop , sentiment);
	
	//add statement
	model.add(stmt1);
	model.add(stmt2);
	model.add(stmt3);*/
	//add property
	texte.addProperty(has , type);
	texte.addProperty(has, aspect);
	//aspect.addProperty(prop, sentiment);

	
	 StmtIterator iter = model.listStatements();

    // affiche l'objet, le prédicat et le sujet de chaque déclaration
    while (iter.hasNext()) {
        Statement stmt      = iter.nextStatement();  // obtenir la prochaine déclaration
        Resource  subject   = stmt.getSubject();     // obtenir le sujet
        Property  predicate = stmt.getPredicate();   // obtenir le prédicat
        RDFNode   object    = stmt.getObject();      // obtenir l'objet

        System.out.print(subject.toString()+ "  "+"\n");
        System.out.print("  " +  predicate.toString()  + "  ");
        if (object instanceof Resource) {
           System.out.print(object.toString());
        } else {
            // l'objet est un littéral
            System.out.println("  " + object.toString()  +  "  ");
        }

			            
	}
model.write(new FileWriter("trial.rdf"));
// model.write(new FileWriter("trial.rdf"), "RDF/XML-ABBREV");
}
}
