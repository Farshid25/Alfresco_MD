package Type_Read_Switch;

import NaturalLangugageApi.Classifying_Content;
import content_reader.*;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;

public class TypeDetection {

    public void mySwitch(String pad)throws Exception {
        Classifying_Content classifying_content = new Classifying_Content();

        FileInputStream inputstream = new FileInputStream(new File(pad));
        Tika tika = new Tika();
        String type = tika.detect(inputstream);
        String result = "";

        System.out.println(type);
        switch (type) {
            case "application/x-tika-ooxml": // PPTX.XLSX.DOCX.
                System.out.println("\n"+"Jouw gekozen type is: " + type +"\n");
                Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();

                    result += apacke_tika_docx.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;

            case "application/pdf":
                System.out.println("\n"+"Jouw gekozen type is:  ^" + type +"^"+"\n");
                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();

                    result += apache_tika_pdf.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;

            case "application/zip": // .ODT
                System.out.println("\n" + "Jouw gekozen type is:  ^" + type + "^" + "\n");
                Apache_Tika_ODT apache_tika_odt = new Apache_Tika_ODT();

                    result += apache_tika_odt.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;

            case "text/plain": // .txt
                System.out.println("\n" + "Jouw gekozen type is:  ^" + type + "^" + "\n");
                Apache_Tika_TEXT apache_tika_text = new Apache_Tika_TEXT();

                    result += apache_tika_text.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;

            case "application/vnd.ms-word2006ml": // .xml
                System.out.println("\n" + "Jouw gekozen type is:  ^" + type + "^" + "\n");
                Apache_Tika_XML apache_tika_xml = new Apache_Tika_XML();

                    result += apache_tika_xml.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;
        }
    }
    public static void main(String[] args) throws Exception{
        TypeDetection t = new TypeDetection();
         t.mySwitch("src\\main\\resources\\Txt.txt");
    }
}



