package services.NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;

import model.Category;
import services.content_reader.Apache_Tika_PDF;
import services.content_reader.Apacke_Tika_Docx;


public class Classifying_Content {

    public String classifyFile(String path  , Category cat ) throws Exception {

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

                cat.setCategory(category.getName());
                cat.setConfidence(category.getConfidence());

            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception{
        Category ca1 = new Category();
        Classifying_Content cf = new Classifying_Content();
        Apacke_Tika_Docx ap = new Apacke_Tika_Docx();
        cf.classifyFile(ap.readFile("src\\main\\Aanvullende_Files\\MS1.docx"),ca1);
        System.out.println(ca1.getCategory());
        System.out.println(ca1.getConfidence());
    }
 }






