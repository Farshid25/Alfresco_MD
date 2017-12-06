package NaturalLangugageApi;

import com.google.cloud.language.v1.*;

import com.google.cloud.language.v1.Document.Type;

import java.util.List;
import content_reader.*;

public class Analyze_Sentiment {
    public void analyzeSentiment(String text)throws  Exception{
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment == null) {
                System.out.println("No sentiment found");
            } else {
                System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
                System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Analyze_Sentiment sentiment = new Analyze_Sentiment();
        Apacke_Tika_Docx docx = new Apacke_Tika_Docx();
        Apache_Tika_PDF pdf = new Apache_Tika_PDF();
        sentiment.analyzeSentiment(docx.readFile("src\\main\\Aanvullende_Files\\test2.docx\\"));
    }
}

