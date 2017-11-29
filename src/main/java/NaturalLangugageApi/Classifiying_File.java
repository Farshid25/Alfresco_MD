package NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;
import content_reader.Apache_Poi_DocxTest;


public class Classifiying_File {

    public static void classifyFile(String path) throws Exception {
        // [START classify_file]
        // Instantiate a beta client : com.google.cloud.language.v1beta2.LanguageServiceClient
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // set the GCS content URI path
            Document doc = Document.newBuilder()
                    .setContent(path)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            ClassifyTextRequest request = ClassifyTextRequest.newBuilder()
                    .setDocument(doc)
                    .build();
            // detect categories in the given file
            ClassifyTextResponse response = language.classifyText(request);

            for (ClassificationCategory category : response.getCategoriesList()) {
                System.out.printf("Category name : %s, Confidence : %.3f\n",
                        category.getName(), category.getConfidence());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Apache_Poi_DocxTest doc = new Apache_Poi_DocxTest();
        classifyFile(doc.readFile("src\\main\\resources\\NewFafa(Docx).docx"));
    }
}



