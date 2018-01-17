package services.NaturalLangugageApi;

import com.google.cloud.language.v1beta2.*;

import model.Category;
import services.content_reader.Apache_Tika_ODT;
import services.content_reader.Apache_Tika_PDF;
import services.content_reader.Apache_Tika_TEXT;
import services.content_reader.Apacke_Tika_Docx;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Classifying_Content {

    public List<Category> classifyFile(String path) throws Exception {
        List<Category> catList = new ArrayList<>();
        //public Category classifyFile(String path, Category cat ) throws Exception {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()
                    .setContent(path)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();
            ClassifyTextRequest request = ClassifyTextRequest.newBuilder()
                    .setDocument(doc)
                    .build();

            ClassifyTextResponse response = language.classifyText(request);


            for (ClassificationCategory category : response.getCategoriesList()) {

//                cat.setCategory(category.getName());
//                cat.setConfidence(category.getConfidence());
                catList.add(new Category(category.getName(), category.getConfidence()));
            }
        }
        return catList;
    }

//    public static void main(String[] args) throws Exception {
//        Classifying_Content cc = new Classifying_Content();
//        Apacke_Tika_Docx apacheDoc = new Apacke_Tika_Docx();
//        Apache_Tika_PDF pdfReader = new Apache_Tika_PDF();
//        Apache_Tika_ODT odt = new Apache_Tika_ODT();
//        //Category category = new Category();
//
//        try {
//            File f = new File("src\\main\\Aanvullende_Files\\MS1.docx\\");
//
//            List<Category> categories = cc.classifyFile(apacheDoc.readFile(f));
//            //  cc.classifyFile(apacheDoc.readFile("src\\main\\Aanvullende_Files\\EthicsFinal.docx\\"),category);
////
////            //ArrayList<Category> pdf = cc.classifyFile(pdfReader.readFile("src\\main\\Aanvullende_Files\\Sollicitatie.docx\\"));
//            for (Category category : categories) {
//
//                if (category != null) {
//                    //System.out.println("hiiiiiiiiiii");
//                    System.out.println("cat " + category);
//                    System.out.println("get cat " + category.getCategory());
//                    System.out.println("get con " + category.getConfidence());
//                    //txtField1.appendText(category.getCategory());
//                } else {
//                    System.out.println("leeeeeg");
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//    }
}





