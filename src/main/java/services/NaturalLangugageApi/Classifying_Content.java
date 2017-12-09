package services.NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;

import services.content_reader.Apache_Tika_PDF;
import services.content_reader.Apacke_Tika_Docx;


public class Classifying_Content {

    public String classifyFile(String path /*, Docx object*/) throws Exception {

        String text = "";


        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()

                    .setContent(path)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            ClassifyTextRequest request = ClassifyTextRequest.newBuilder()
                    .setDocument(doc)
                    .build();

            ClassifyTextResponse response = language.classifyText(request);


            for (ClassificationCategory category : response.getCategoriesList()) {
//                    System.out.printf("Category name : %s, Confidence : %.3f\n",
//                            docx.setCategory(category.getName()));
                text += "\nJouw categorie is:  ^" + category.getName() + "^";
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
        return text;
    }

    public static void main(String[] args) throws Exception {
        Classifying_Content cf = new Classifying_Content();
        Apache_Tika_PDF pdf = new Apache_Tika_PDF();
        Apacke_Tika_Docx ap = new Apacke_Tika_Docx();

        cf.classifyFile(ap.readFile("src\\main\\Aanvullende_Files\\Sollicitatie2.docx\\"));
    }
}





