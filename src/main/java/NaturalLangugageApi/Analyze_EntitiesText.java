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

public class Analyze_EntitiesText {
    public static void analyzeEntitiesText(String text) throws Exception {

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
            System.out.printf("Entity: %s", entity.getName());
            System.out.printf("Salience: %.3f\n", entity.getSalience());
            System.out.println("Metadata: ");
            for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
                System.out.printf("%s : %s", entry.getKey(), entry.getValue());
            }
            for (EntityMention mention : entity.getMentionsList()) {
                System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
                System.out.printf("Content: %s\n", mention.getText().getContent());
                System.out.printf("Type: %s\n\n", mention.getType());
            }
        }
    }
    }

    public static void main(String[] args) throws Exception {
        analyzeEntitiesText("\"src\\main\\resources\\NewFafa(Docx).docx");//"//farshid is a very handsome man and happy");
        //System.out.println(test("src\\main\\resources\\NewFafa(Docx).docx"));

    }
}