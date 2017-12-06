package content_reader;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Apache_Tika_XML {


        public String readFile(String pad) throws IOException, SAXException, TikaException {

            //detecting the file type
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File(pad));
            ParseContext pcontext = new ParseContext();

            //Open Document Parser
            XMLParser xmlParser  = new XMLParser();
            xmlParser.parse(inputstream, handler, metadata, pcontext);
            String output = "Contents of the document: " + handler.toString();
            return output;
        }

        public static void main(String[] args) throws TikaException, SAXException, IOException {
           Apache_Tika_XML apache_tika_xml  = new Apache_Tika_XML();
            System.out.println(apache_tika_xml.readFile("src\\main\\resources\\Hello.xml"));
        }
    }


