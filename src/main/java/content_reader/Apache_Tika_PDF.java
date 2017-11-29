package content_reader;

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
    public class Apache_Tika_PDF {

        //public static void main(final String[] args) throws IOException, TikaException, SAXException {

        public String result(String path) throws IOException, TikaException, SAXException {

            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(path)); //"src\\main\\resources\\NewPdf.pdf"));
            ParseContext pcontext = new ParseContext();

            //parsing the document using PDF parser
            PDFParser pdfparser = new PDFParser();

            pdfparser.parse(inputstream, handler, metadata, pcontext);


                String output = handler.toString();

            LanguageIdentifier identifier = new LanguageIdentifier(output);
            String language = identifier.getLanguage();
            System.out.println("Language of the given content is : " + language);

            return ("Contents of the PDF :" + output);
    }

        public static void main(String[] args) throws TikaException, IOException, SAXException {
            Apache_Tika_PDF p = new Apache_Tika_PDF();
            System.out.println(p.result("src\\main\\resources\\nlPDF.pdf"));
           // System.out.println(p.result());
        }
    }
