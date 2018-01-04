package services.content_reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.language.LanguageIdentifier;

import org.xml.sax.SAXException;
import services.GoogleTranslationApi.TranslateText;

public class Apache_Tika_PDF {
    TranslateText translateText = new TranslateText();
        //public static void main(final String[] args) throws IOException, TikaException, SAXException {

        public String readFile(File path) throws IOException, TikaException, SAXException {

            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(String.valueOf(path))); //"src\\main\\resources\\NewPdf.pdf"));
            ParseContext pcontext = new ParseContext();

            //parsing the document using PDF parser
            PDFParser pdfparser = new PDFParser();

            pdfparser.parse(inputstream, handler, metadata, pcontext);
                String handel = handler.toString();
                String output = "";

            LanguageIdentifier identifier = new LanguageIdentifier(output);
            String language = identifier.getLanguage();
            System.out.println("Language of the given content is : " + language); System.out.println("\n");
            if (language.equals("nl")) {
                output += translateText.TransIt(handel);
                System.out.println("omgezet naar EN");
            }else{
                output += handel.toString();
                System.out.println("geen NL ");
            }
            return (output);
    }

        public static void main(String[] args) throws TikaException, IOException, SAXException {
            Apache_Tika_PDF p = new Apache_Tika_PDF();
            File pdf = new File("src\\main\\Aanvullende_Files\\poem.pdf");
            System.out.println(p.readFile(pdf));
           // System.out.println(p.result());
        }
    }
