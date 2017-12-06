package content_reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

    public class Apache_Tika_ODT {

        public String readFile(String pad) throws IOException, SAXException, TikaException {

            //detecting the file type
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(pad));
            ParseContext pcontext = new ParseContext();

            //Open Document Parser
            OpenDocumentParser openofficeparser = new OpenDocumentParser();
            openofficeparser.parse(inputstream, handler, metadata, pcontext);
            String output = "Contents of the document: " + handler.toString();
            return output;
        }

        public static void main(String[] args) throws TikaException, SAXException, IOException {
            Apache_Tika_ODT apache_tika_odt = new Apache_Tika_ODT();
            System.out.println(apache_tika_odt.readFile("src\\main\\resources\\ODT.odt"));
        }
    }
