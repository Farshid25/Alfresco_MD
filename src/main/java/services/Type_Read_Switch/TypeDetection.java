package services.Type_Read_Switch;

import model.restModel.Result;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import services.NaturalLangugageApi.Classifying_Content;
import services.content_reader.*;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypeDetection {

    public String getFile(File file) throws IOException, TikaException, SAXException {
        //Classifying_Content classifying_content = new Classifying_Content();

        Tika tika = new Tika();
        String type = tika.detect(file);
        String result = "";

        switch (type) {
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": //  .DOCX

                System.out.println("Your File Type is: " + type);
                Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();
                result += apacke_tika_docx.readFile(file);
                break;

            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": //  .XLSX

                System.out.println("Your File Type is: " + type);
                Apacke_Tika_Docx xlsx = new Apacke_Tika_Docx();
                result += xlsx.readFile(file);
                break;

            case "application/vnd.openxmlformats-officedocument.presentationml.presentation": // .PPTX

                System.out.println("Your File Type is: " + type);
                Apacke_Tika_Docx pptx = new Apacke_Tika_Docx();
                result += pptx.readFile(file);
                break;

            case "application/pdf":                                         //.pdf
                System.out.println("Your File Type is: " + type);
                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();
                result += apache_tika_pdf.readFile(file);
                break;

            case "application/vnd.oasis.opendocument.text":                 //.odt
                System.out.println("Your File Type is: " + type);
                Apache_Tika_ODT apache_tika_odt = new Apache_Tika_ODT();
                result += apache_tika_odt.readFile(file);
                break;

            case "text/plain":
                System.out.println("Your File Type is: " + type);
                Apache_Tika_TEXT apache_tika_text = new Apache_Tika_TEXT();
                result += apache_tika_text.readFile(file);
                break;
        }

        return result;
    }

    public String mySwitch(String pad) throws Exception {

        String result = "";

        Classifying_Content classifying_content = new Classifying_Content();

        FileInputStream inputstream = new FileInputStream(new File(pad));
        Tika tika = new Tika();
        String type = tika.detect(inputstream);

        //System.out.println(type);
        switch (type) {
            case "application/x-tika-ooxml": // PPTX.XLSX.DOCX.
                //result.setDocType(type);

                System.out.println("Your File Type is: " + type);
                Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();

                //result += classifying_content.classifyFile(apacke_tika_docx.readFile(pad)) ;


                break;
//            case "application/pdf":
//                result.setDocType(type);
//                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();
//
////                result.setTaal(classifying_content.classifyFile(apache_tika_pd.freadFile(pad)));
//                result.setCategorie(classifying_content.classifyFile(apache_tika_pdf.readFile(pad)));
//                break;
//            case "application/zip": // .ODT
//                result.setDocType(type);
//                Apache_Tika_ODT apache_tika_odt = new Apache_Tika_ODT();
//
//                result.setTaal(classifying_content.classifyFile(apache_tika_odt.readFile(pad)));
//break;
//            case "text/plain": // .txt
//                result.setDocType(type);
//                Apache_Tika_TEXT apache_tika_text = new Apache_Tika_TEXT();
//
//                result.setTaal(classifying_content.classifyFile(apache_tika_text.readFile(pad)));
//break;
//            case "application/vnd.ms-word2006ml": // .xml
//                result.setDocType(type);
//                Apache_Tika_XML apache_tika_xml = new Apache_Tika_XML();
//
//                result.setTaal(classifying_content.classifyFile(apache_tika_xml.readFile(pad)));
//                break;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        TypeDetection typeDetection = new TypeDetection();
        //typeDetection.getFile()

        System.out.println(typeDetection.mySwitch("src\\main\\Aanvullende_Files\\MS1.docx"));
    }
}



