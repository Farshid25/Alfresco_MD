package services.GoogleTranslationApi;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import services.content_reader.*;

import java.io.File;
import java.io.IOException;

public class TranslateText {
    public String TransIt(String source){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        String print ="";
        Translation translation =
                translate.translate(
                        source,
                        TranslateOption.sourceLanguage("nl"),
                        TranslateOption.targetLanguage("en"));
        //System.out.println(translation.getTranslatedText());
        print += translation.getTranslatedText();
        return print;
    }
    public String TranslateEngels(String source){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        String print ="";
        Translation translation =
                translate.translate(
                        source,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("nl"));
        //System.out.println(translation.getTranslatedText());
        print += translation.getTranslatedText();
        return print;
    }

//    public static void main(String[] args) throws TikaException, IOException, SAXException {
//        TranslateText t = new TranslateText();
//        Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();
//        File file = new File("src\\main\\Aanvullende_Files\\MS1.docx");
//        String s =  t.TranslateEngels(apacke_tika_docx.readFile(file));
//        System.out.println(s);
//    }
}