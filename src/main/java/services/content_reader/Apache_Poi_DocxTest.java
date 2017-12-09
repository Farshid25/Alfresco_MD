package services.content_reader;

import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;


import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument; // documenten docx

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Apache_Poi_DocxTest {
    public String readFile(String filePath) throws POIXMLException, IOException, InvalidFormatException {
        //String path = "src\\main\\resources\\excel.xlsx";
        String text = "";

        try {
            FileInputStream file = new FileInputStream(new File(String.valueOf(filePath)));
            XWPFDocument doc=new XWPFDocument(OPCPackage.open(file));

            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
              text += extractor.getText();

           while (extractor != null){
              return (text);
           }
        }catch (POIXMLException e) {
            System.out.println(e);
        }
        return text;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Apache_Poi_DocxTest d = new Apache_Poi_DocxTest();
        String print = d.readFile("src\\main\\resources\\NewFafa(Docx).docx");
        System.out.println(print);
       // System.out.println(t.Getxlsx("src\\main\\resources\\excel.xlsx"));
    }
}

