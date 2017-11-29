package Type_Read_Switch;

import NaturalLangugageApi.Classifying_Content;
import content_reader.Apache_Poi_DocxTest;
import content_reader.Apache_Tika_PDF;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;

public class TypeDetection {
//    public String result(String path) throws IOException, TikaException, SAXException {//
//        FileInputStream inputstream = new FileInputStream(new File(path));//
//        Tika tika = new Tika();//
//        String type = tika.detect(inputstream);
//        String output = "";
//        if (type.equals("application/pdf")) {
//
//            output += "Jouw file-type is: "+ type.toString();
//
//        }else if (type.equals("application/x-tika-ooxml")) {
//            //System.out.println(type);
//            output += "Jouw file-type is: "+ type.toString();
//
//        } else System.out.println("Jouw file-type is niet gevonden!");//
//        System.out.println("\n");
//        System.out.println(output);//
//        return output;//    }

    public void mySwitch(String pad)throws Exception{

        FileInputStream inputstream = new FileInputStream(new File(pad));
        Tika tika = new Tika();
        String type = tika.detect(inputstream);
        String result = "";

        Classifying_Content classifying_content = new Classifying_Content();


        switch (type){
            case "application/x-tika-ooxml":
                System.out.println("\n"+"Jouw gekozen type is: " + type +"\n");
                Apache_Poi_DocxTest apache_poi_docxTest = new Apache_Poi_DocxTest();

                    result += apache_poi_docxTest.readFile(pad);
                    classifying_content.classifyFile(result);
                    //return result;
                    break;

            case "application/pdf":
                System.out.println("\n"+"Jouw gekozen type is:  ^" + type +"^"+"\n");
                Apache_Tika_PDF apache_tika_pdf = new Apache_Tika_PDF();

                    result += apache_tika_pdf.result(pad);
                    classifying_content.classifyFile(result);
                    break;
        }//return result;
    }

    public static void main(String[] args) throws Exception{
        TypeDetection t = new TypeDetection();
         t.mySwitch("src\\main\\resources\\Tetris-PDF.pdf");
    }
}



