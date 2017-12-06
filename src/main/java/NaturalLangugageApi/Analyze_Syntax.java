package NaturalLangugageApi;

import com.google.cloud.language.v1.*;

import com.google.cloud.language.v1.Document.Type;

import java.util.List;
import content_reader.*;

public class Analyze_Syntax {
    public List<Token> analyzeSyntax(String text) throws Exception{
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder()
                    .setContent(text)
                    .setType(Type.PLAIN_TEXT)
                    .build();
            AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder()
                    .setDocument(doc)
                    .setEncodingType(EncodingType.UTF16)
                    .build();

            AnalyzeSyntaxResponse response = language.analyzeSyntax(request);                   // Lijkt onzin te zijn voor jou

            for (Token token : response.getTokensList()) {
                System.out.printf("\tText: %s\n", token.getText().getContent());
                System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());

            }
            return response.getTokensList();
        }
    }

    public static void main(String[] args) throws Exception{
        Analyze_Syntax syntax = new Analyze_Syntax();
        Apacke_Tika_Docx docx = new Apacke_Tika_Docx();
        Apache_Tika_PDF pdf = new Apache_Tika_PDF();
        syntax.analyzeSyntax(docx.readFile("src\\main\\Aanvullende_Files\\test2.docx\\"));
    }

}
