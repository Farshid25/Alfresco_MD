package Type_Read_Switch;

import NaturalLangugageApi.Classifying_Content;
import content_reader.Apache_Poi_DocxTest;
import content_reader.Apache_Tika_PDF;
import content_reader.Apacke_Tika_Docx;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;

public class TypeDetection {

    public void mySwitch(String pad)throws Exception{
        Classifying_Content classifying_content = new Classifying_Content();

        FileInputStream inputstream = new FileInputStream(new File(pad));
        Tika tika = new Tika();
        String type = tika.detect(inputstream);
        String result = "";

        switch (type){
            case "application/x-tika-ooxml":
                System.out.println("\n"+"Jouw gekozen type is: " + type +"\n");
                Apacke_Tika_Docx apacke_tika_docx = new Apacke_Tika_Docx();

                    result += apacke_tika_docx.readFile(pad);
                    classifying_content.classifyFile(result);
                    //return result;
                    break;

            case "application/pdf":
                System.out.println("\n"+"Jouw gekozen type is:  ^" + type +"^"+"\n");
                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();

                    result += apache_tika_pdf.readFile(pad);
                    classifying_content.classifyFile(result);
                    break;
        }//return result;
    }

    public static void main(String[] args) throws Exception{
        TypeDetection t = new TypeDetection();
         t.mySwitch("src\\main\\resources\\Docx.docx");
    }
}



