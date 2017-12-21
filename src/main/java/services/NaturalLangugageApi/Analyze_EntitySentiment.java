package services.NaturalLangugageApi;

import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import services.content_reader.*;

public class Analyze_EntitySentiment {
    public void analyzeEntitySentiment(String text) throws Exception {

        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()  //blijkt onzin te zijn voor jou!
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
            for (Entity entity : response.getEntitiesList()) {
                System.out.printf("Entity: %s\n", entity.getName());
                System.out.printf("Salience: %.3f\n", entity.getSalience());
                System.out.printf("Sentiment : %s\n", entity.getSentiment());
                for (EntityMention mention : entity.getMentionsList()) {
//                    System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
//                    System.out.printf("Content: %s\n", mention.getText().getContent());
                    System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
                    System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
                    System.out.printf("Type: %s\n\n", mention.getType());
                }
            }
        }
    }

//    public static void main(String[] args) throws Exception{
//        Analyze_EntitySentiment analyze_entitySentiment = new Analyze_EntitySentiment();
//        Apache_Tika_PDF pdfReader = new Apache_Tika_PDF();
//        Apacke_Tika_Docx docx = new Apacke_Tika_Docx();
//        analyze_entitySentiment.analyzeEntitySentiment(docx.readFile("src\\main\\Aanvullende_Files\\test2.docx"));
//    }
}
