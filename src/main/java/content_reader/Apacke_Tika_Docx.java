package content_reader;

//import org.apache.tika.exception.TikaException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* leest PPTX.XLSX.DOCX.*/

public class Apacke_Tika_Docx {

    public String readFile(String path) throws IOException, TikaException, SAXException {

        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(path));
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        OOXMLParser ooxml = new OOXMLParser();

        ooxml.parse(inputstream, handler, metadata, pcontext);

            String output = handler.toString();

        LanguageIdentifier identifier = new LanguageIdentifier(output);
        String language = identifier.getLanguage();
        System.out.printf("Language of the given content is: %s",language); System.out.println("\n");


        return ("Contents of the PDF :" + output);
    }
    public static void main(String[] args) throws TikaException, SAXException, IOException {
        Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();
        System.out.println(apacke_tika_docx.readFile("src\\main\\Aanvullende_Files\\test1.docx")); /* leest PPTX.XLSX.DOCX.*/
    }
}
