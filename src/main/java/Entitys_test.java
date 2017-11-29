import java.io.IOException;
import java.util.Map;

import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import content_reader.Apache_Poi_DocxTest;


public class Entitys_test {
    public void Transfer(String text) throws Exception {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();
            //Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
            AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();
            AnalyzeEntitiesResponse response = language.analyzeEntities(request);

            for (Entity entity:response.getEntitiesList()){
                System.out.printf("Entity: %s \n", entity.getName());
                System.out.printf("Salience: %.3f\n", entity.getSalience());

                System.out.println("Metadata: ");
                for(Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()){
                    System.out.printf("%s : %s", entry.getKey(), entry.getValue());
                }
                for (EntityMention mention : entity.getMentionsList()) {
                    System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
                    System.out.printf("Content: %s\n", mention.getText().getContent());
                    System.out.printf("Type: %s\n\n", mention.getType());
                }
            }

            System.out.printf("Text: %s%n", text);
            //System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Entitys_test p = new Entitys_test();
        //PdfTest_Tika pdf = new PdfTest_Tika();
        Apache_Poi_DocxTest t = new Apache_Poi_DocxTest();
        p.Transfer(t.readFile("src\\main\\resources\\NewFafa(Docx).docx"));

    }
}



