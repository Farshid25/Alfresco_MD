package NaturalLangugageApi;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;

import java.util.Map;

import content_reader.*;

public class Analyze_Entities {
    public void analyzeEntities(String text) throws Exception {

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();
            AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeEntitiesResponse response = language.analyzeEntities(request);
        for (Entity entity : response.getEntitiesList()) {

            System.out.printf("Entity: %s\n", entity.getName());
            System.out.printf("Type: %s\n", entity.getType()); //person
            System.out.printf("Salience: %.3f\n", entity.getSalience());
//            System.out.println("Metadata: ");
//            for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
//                System.out.printf("%s : %s", entry.getKey(), entry.getValue());
//            }
            for (EntityMention mention : entity.getMentionsList()) {

//                System.out.printf("Content: %s\n", mention.getText().getContent());           // alleen zinnin i.c.m. Sentences, anders geen input.
                System.out.printf("Type: %s\n\n", mention.getType()); //common/proper
            }
        }
    }
    }

    public static void main(String[] args) throws Exception {
        Analyze_Entities analyze_entitiesText = new Analyze_Entities();
//        Apache_Tika_PDF pdfReader = new Apache_Tika_PDF();
//        analyze_entitiesText.analyzeEntities(pdfReader.readFile("src\\main\\Aanvullende_Files\\poem.pdf"));

        Apacke_Tika_Docx docxReader = new Apacke_Tika_Docx();
        analyze_entitiesText.analyzeEntities(docxReader.readFile("src\\main\\Aanvullende_Files\\test2.docx\\"));
    }
}