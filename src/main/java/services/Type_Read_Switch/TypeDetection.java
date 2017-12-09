package services.Type_Read_Switch;

import model.restModel.Result;
import services.NaturalLangugageApi.Classifying_Content;
import services.content_reader.*;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;

public class TypeDetection {

    public Result mySwitch(String pad)throws Exception {

        Result result = new Result();

        Classifying_Content classifying_content = new Classifying_Content();

        FileInputStream inputstream = new FileInputStream(new File(pad));
        Tika tika = new Tika();
        String type = tika.detect(inputstream);

        System.out.println(type);
        switch (type) {
            case "application/x-tika-ooxml": // PPTX.XLSX.DOCX.
                result.setDocType(type);
                Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();

                result.setTaal(classifying_content.classifyFile(apacke_tika_docx.readFile(pad)));

            case "application/pdf":
                result.setDocType(type);
                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();

                result.setTaal(classifying_content.classifyFile(apache_tika_pdf.readFile(pad)));

            case "application/zip": // .ODT
                result.setDocType(type);
                Apache_Tika_ODT apache_tika_odt = new Apache_Tika_ODT();

                result.setTaal(classifying_content.classifyFile(apache_tika_odt.readFile(pad)));

            case "text/plain": // .txt
                result.setDocType(type);
                Apache_Tika_TEXT apache_tika_text = new Apache_Tika_TEXT();

                result.setTaal(classifying_content.classifyFile(apache_tika_text.readFile(pad)));

            case "application/vnd.ms-word2006ml": // .xml
                result.setDocType(type);
                Apache_Tika_XML apache_tika_xml = new Apache_Tika_XML();

                result.setTaal(classifying_content.classifyFile(apache_tika_xml.readFile(pad)));
        }
        return result;
    }
}



