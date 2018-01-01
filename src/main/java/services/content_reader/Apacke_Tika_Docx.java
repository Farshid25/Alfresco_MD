package services.content_reader;

//import org.apache.tika.exception.TikaException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import services.GoogleTranslationApi.TranslateText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* leest PPTX.XLSX.DOCX.*/

public class Apacke_Tika_Docx {
    TranslateText translateText = new TranslateText();

    public String readFile(File path) throws IOException, TikaException, SAXException {

        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(String.valueOf(path)));
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        OOXMLParser ooxml = new OOXMLParser();

        ooxml.parse(inputstream, handler, metadata, pcontext);

            String handel = handler.toString();
            String output = "";

        LanguageIdentifier identifier = new LanguageIdentifier(handel);
        String language = identifier.getLanguage();
        System.out.printf("Language of the given content is: %s",language); System.out.println("\n");
        if (language.equals("en")) {
            output += translateText.TransIt(handel);
            System.out.println("ja");
        }else{
            output += handel.toString();
            System.out.println("nee");
        }
        return  (output);
    }

    public static void main(String[] args) throws TikaException, SAXException, IOException {
        Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();
        File file = new File("src\\main\\Aanvullende_Files\\NL_tekst.docx");
       System.out.println(apacke_tika_docx.readFile(file)); /* leest PPTX.XLSX.DOCX.*/

    }
}
