package content_reader;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;

import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Apache_Tika_TEXT {

    public String result(String path) throws IOException, TikaException, SAXException {

        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(path));
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        TXTParser txtParser = new TXTParser();
        txtParser.parse(inputstream, handler, metadata, pcontext);

        //getting the content of the document
        String output = "Contents of the document :" + handler.toString();
        return (output);
    }

    public static void main(String[] args) throws TikaException, SAXException, IOException {
    Apache_Tika_TEXT apache_tika_text = new Apache_Tika_TEXT();
        System.out.println(apache_tika_text.result("src\\main\\resources\\Txt.txt"));
    }
}
