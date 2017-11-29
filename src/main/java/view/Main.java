package view;

import NaturalLangugageApi.Classifying_Content;
import content_reader.Apache_Tika_PDF;
import model.Docx;
import content_reader.Apache_Poi_DocxTest;



public class Main {

    public static void main(String[] args) throws Exception {
        Apache_Tika_PDF apt = new Apache_Tika_PDF();
        Apache_Poi_DocxTest docx = new Apache_Poi_DocxTest();

//            Apache_POI_DocxTest doc = new Apache_POI_DocxTest();
        Docx object = new Docx();

        Classifying_Content classifying_content = new Classifying_Content();
        classifying_content.classifyFile(apt.result("src\\main\\resources\\Tetris-PDF.pdf"));
//            object.toString();
        //System.out.println(object.toString());
    }
}
