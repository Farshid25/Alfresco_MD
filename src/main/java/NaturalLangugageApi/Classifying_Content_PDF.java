package NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;

import content_reader.Apache_Tika_PDF;


public class Classifying_Content_PDF {

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
//                    System.out.printf("Category name : %s, Confidence : %.3f\n",
//                            docx.setCategory(category.getName()));
//                object.setCategory(category.getName());
//                object.setConfidence(category.getConfidence());
                System.out.println("Name: " + category.getName());
                System.out.println("Condidence: " + category.getConfidence());
                //category.getName(), category.getConfidence());
//                    System.out.println(object.getConfidence());
//                    System.out.println(object.getCategory());

            }
        }
    }

}


