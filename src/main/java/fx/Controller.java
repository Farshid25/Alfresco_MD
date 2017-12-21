package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import jdk.nashorn.internal.ir.WhileNode;
import model.Category;
import model.Entity;
import org.apache.tika.detect.TextDetector;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import services.NaturalLangugageApi.Analyze_Entities;
import services.NaturalLangugageApi.Classifying_Content;
import services.Type_Read_Switch.TypeDetection;
import services.content_reader.Apacke_Tika_Docx;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.*;
import java.lang.*;

//import services.NaturalLangugageApi.Analyze_Entities;

public class Controller implements Initializable {
    TypeDetection typeDetection = new TypeDetection();
    Apacke_Tika_Docx apacheDoc = new Apacke_Tika_Docx();
    Classifying_Content cc = new Classifying_Content();
    Analyze_Entities ae = new Analyze_Entities();
    model.Entity entityobject = new model.Entity();

    Category cat = new Category();

    @FXML
    private Button btn1;
    @FXML
    public TextArea entName1;
    @FXML
    public TextArea entSaile2;
    @FXML
    public TextArea entType3;
    @FXML
    private TextField txtField1;
    @FXML
    private TextField txtField2;
    @FXML
    private TextField subject;
    @FXML
    private FileChooser fileChooser = new FileChooser();
//    @FXML
//    TextDetector textDetector = new TextDetector();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnAction((ActionEvent event) -> {
            File file = fileChooser.showOpenDialog(btn1.getScene().getWindow());
               //typeDetection.getFile(file);

            //file = fileChooser.showOpenDialog(btn1.getScene().getWindow());
           /// if (file.get)

            ArrayList<Entity> enList = null;
            try {
                //enList = ae.analyzeEntities(typeDetection.getFile(file));
                enList = ae.analyzeEntities(typeDetection.getFile(file));
                for (Entity f : enList){
                    System.out.println("Controller test " +f.getName());
                    entName1.appendText(f.getName()+ " ");

                }

//            try {
//                 enList = ae.analyzeEntities(typeDetection.getFile(file));     //(file.getAbsolutePath()));
//                Map<String, Float> defeaultMap = new HashMap<>();
//
//                for (Entity hash : enList) {
//                    defeaultMap.put(hash.getName(), hash.getSalience());
//                }
//                for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
//                    System.out.println(sal.getKey() + " " +sal.getValue());
//                }
//            } catch (TikaException e) {
//                e.printStackTrace();
//            } catch (SAXException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


// Entities & Saliences fix
        ArrayList<Category> categories = null;

                    categories = cc.classifyFile(typeDetection.getFile(file));   //file.getAbsolutePath()));

                    for (Category category : categories) {
                        if (category != null) {
                            System.out.println("get cat " + category.getCategory());
                            System.out.println("get con "+ category.getConfidence());
                            txtField1.appendText(category.getCategory());
                        } else {
                            System.out.println("leeg");
                        }
                    }
                    for (Category category : categories){
                        Float o = category.getConfidence() * 100;
                        String b = o.toString();
                        txtField2.appendText("% "+ String.valueOf(Math.round(Float.parseFloat(b))));
                    }


                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });}}
//                Map<String, Float> mmap = new HashMap<>();
//                for (Entity i : enList) mmap.put(i.getName(), i.getSalience());
//
//                for (Map.Entry<String, Float> sal : mmap.entrySet()) {
//                    entName1.appendText(sal.getKey() + " , ");
//                    Float omzettenFloat = sal.getValue() * 100;
//                    String afrondenFloat = omzettenFloat.toString();
//                    entSaile2.appendText("%" + Math.round(Float.parseFloat(afrondenFloat)) + " , ");
//                }
//
////  Subject fix
//                Map<String, Float> map = new HashMap<>();
//
//                for (Entity hash : enList) {
//                    map.put(hash.getName(), hash.getSalience());
//                }
//
//                ArrayList<Float> salien = new ArrayList<>();
//
//                for (Map.Entry<String, Float> sal : map.entrySet()) {
//                    salien.add(sal.getValue());
//                }
//
//                float max = 0;
//                try {
//                    max += Collections.max(salien);
//
//                    Map.Entry<String, Float> print = null;
//                    for (Map.Entry<String, Float> e : map.entrySet()) {
//                        if (e.getValue().equals(max)) {
//                            print = e;
//                        }
//                    }
//                    if (print != null) {
//                        subject.appendText(print.getKey());
//                    } else {
//                        System.out.println("error hashmap");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            } catch (TikaException e) {
//                e.printStackTrace();
//            } catch (SAXException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}




