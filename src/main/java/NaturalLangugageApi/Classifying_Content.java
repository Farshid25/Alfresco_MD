package NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;

import content_reader.Apache_Poi_DocxTest;
import model.Docx;


public class Classifying_Content {

        public void classifyFile(String path /*, Docx object*/) throws Exception {
            //Apache_Poi_DocxTest ap = new Apache_Poi_DocxTest();
            String text = "";
            // [START classify_file]
            // Instantiate a beta client : com.google.cloud.language.v1beta2.LanguageServiceClient
            try (LanguageServiceClient language = LanguageServiceClient.create()) {
                // set the GCS content URI path

                Document doc = Document.newBuilder()
                        //.setContent(ap.readFile(path))
                        .setContent(path)
                        .setType(Document.Type.PLAIN_TEXT)
                        .build();
                ClassifyTextRequest request = ClassifyTextRequest.newBuilder()
                        .setDocument(doc)
                        .build();
                // detect categories in the given file
                ClassifyTextResponse response = language.classifyText(request);

                for (ClassificationCategory category : response.getCategoriesList()) {
//                    System.out.printf("Category name : %s, Confidence : %.3f\n",
//                            docx.setCategory(category.getName()));
                     text += "en jouw categorie is:  ^"+ category.getName()+"^";
//                    object.setCategory(category.getName());
//                    object.setConfidence(category.getConfidence());
//                    System.out.println("Name: "+category.getName());
//                    System.out.println("Condidence: "+category.getConfidence());
                            //category.getName(), category.getConfidence());
//                    System.out.println(object.getConfidence());
                    //System.out.println(category.getName());
                   // object.toString();
                }
            }
            System.out.println(text);
        }
    }





