package fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Category;
import model.Entity;
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
    public Button newButton;
    TypeDetection typeDetection = new TypeDetection();
    Apacke_Tika_Docx apacheDoc = new Apacke_Tika_Docx();
    Classifying_Content cc = new Classifying_Content();
    Analyze_Entities ae = new Analyze_Entities();
    model.Entity entityobject = new model.Entity();

    Category cat = new Category();

    @FXML
    private Button btn1;
    @FXML
    private Button serkan;
    @FXML
    public TextArea entities;
    @FXML
    public TextArea salience;
    @FXML
    public TextArea test;
    @FXML
    private TextField category;
    @FXML
    private TextField confidence;
    @FXML
    private TextField subject;
    @FXML
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(btn1.getScene().getWindow());

            ArrayList<Entity> enList = null;

            try {
                enList = ae.analyzeEntities(typeDetection.getFile(file));     //(file.getAbsolutePath()));
                Map<String, Float> defeaultMap = new HashMap<>();

                for (Entity hash : enList) {
                    defeaultMap.put(hash.getName(), hash.getSalience());
                }
                for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                    System.out.println(sal.getKey() + " " + sal.getValue());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

// Entities & Saliences fix
            ArrayList<Category> categories = null;

            try {
                categories = cc.classifyFile(typeDetection.getFile(file));   //file.getAbsolutePath()));

                for (Category cat : categories) {
                    if (category != null) {
                        System.out.println("get cat " + cat.getCategory());
                        System.out.println("get con " + cat.getConfidence());
                        category.appendText(cat.getCategory());
                    } else {
                        System.out.println("leeg");
                    }
                }
                for (Category category : categories) {
                    Float o = category.getConfidence() * 100;
                    String b = o.toString();
                    confidence.appendText("% " + String.valueOf(Math.round(Float.parseFloat(b))));
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Map<String, Float> mmap = new HashMap<>();
            if (enList != null) {
                for (Entity i : enList) mmap.put(i.getName(), i.getSalience());
            }

            for (Map.Entry<String, Float> sal : mmap.entrySet()) {
                entities.appendText(sal.getKey() + " , ");
                Float omzettenFloat = sal.getValue() * 100;
                String afrondenFloat = omzettenFloat.toString();
                salience.appendText("%" + Math.round(Float.parseFloat(afrondenFloat)) + " , ");
            }

//
////  Subject fix
            Map<String, Float> map = new HashMap<>();

            for (Entity hash : enList) {
                map.put(hash.getName(), hash.getSalience());
            }

            ArrayList<Float> salien = new ArrayList<>();

            for (Map.Entry<String, Float> sal : map.entrySet()) {
                salien.add(sal.getValue());
            }

            float max = 0;

            try {
                max += Collections.max(salien);

                Map.Entry<String, Float> print = null;
                for (Map.Entry<String, Float> en : map.entrySet()) {
                    if (en.getValue().equals(max)) {
                        print = en;
                    }
                }
                if (print != null) {
                    subject.appendText(print.getKey());
                } else {
                    System.out.println("error hashmap");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        newButton.setOnAction(
            e -> {
                List list =
                        fileChooser.showOpenMultipleDialog(newButton.getScene().getWindow());
                test.clear();

                for (Object i : list) {
                    if (i instanceof File) {
                        test.appendText(((File) i).getAbsolutePath());
                    }
                }
            }
        );
    }

}



