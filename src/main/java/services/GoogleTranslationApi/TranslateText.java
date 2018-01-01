package services.GoogleTranslationApi;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateText {
    public String TransIt(String source){
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

}