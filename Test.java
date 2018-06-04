import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {


        public static void tmain(String[] args) throws IOException, InterruptedException {

            /*
             * 1. FIRST STEP INVOLVES PREPROCESSING, i.e, REMOVAL OF ERRANT SYMBOLS,
             * STOP WORD REMOVAL, SENTENCE SEGMENTATION, REMOVAL OF UNNECESSARY
             * SPACES
             */
            //String a = "C:\\Users\\Helmi\\eclipse-workspace\\stanford\\trial.txt ;
            //issue1: removeStopWords
            Preprocess preprocess = new Preprocess("trial.txt", "preprocessedFile");

            /*
             * 2. THE SECOND STEP INVOLVES GENERATION OF NOUNS, i.e, GENERATE ALL
             * POSSIBLE NOUN FORMS, PARSE THE PROCESSED REVIEWS, TO GENERATE NOUNS
             * THE NOUNS ARE RECORDED AS "nounsFile" AND "parsedFile" CAN BE USED
             * FOR DEBUGGING ONE MAJOR IMPROVEMENT-->IF THE "parsedFile" IS ALREADY
             * PRESENT THEN PARSING WILL NOT TAKE PLACE AGAIN..THE PREVIOUS NOUN
             * FILE WILL BE USED TO RETURN THE NOUNS
             */
            NounsGenerator ng = new NounsGenerator("preprocessedFile");
            ng.generateAspects();
         System.out.println(ng.getNounsListString() + "\n");
        }
    }