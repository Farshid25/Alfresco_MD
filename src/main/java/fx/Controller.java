package fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Category;
import model.Entity;
import services.GoogleTranslationApi.TranslateText;
import services.NaturalLangugageApi.Analyze_Entities;
import services.NaturalLangugageApi.Classifying_Content;
import services.Type_Read_Switch.TypeDetection;
import services.content_reader.Apacke_Tika_Docx;

import java.io.File;
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
    private Button newButton;
    @FXML
    public TextArea entities;
    @FXML
    public TextArea salience;
    @FXML
    public TextArea test1;
    @FXML
    public TextArea test2;
    @FXML
    public TextArea test3;
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
            category.clear(); confidence.clear(); subject.clear(); entities.clear(); salience.clear();
            File file = fileChooser.showOpenDialog(btn1.getScene().getWindow());

           List<Entity> enList = null;
            Map<String, Float> defeaultMap = new HashMap<>();
            try {
                enList = ae.analyzeEntities(typeDetection.getFile(file));     //(file.getAbsolutePath()));


                for (Entity hash : enList) {
                    defeaultMap.put(hash.getName(), hash.getSalience());
                }
                for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                    //System.out.println("hi");
                    System.out.println(sal.getKey() + " " + sal.getValue());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

// Entities & Saliences fix
            List<Category> categories = null;
            TranslateText translate = new TranslateText();
            try {
                categories = cc.classifyFile(typeDetection.getFile(file));   //file.getAbsolutePath()));

                for (Category cat : categories) {
                    if (category != null) {
                        System.out.println("get cat " + cat.getCategory());
/*vertaal naar nl*/     System.out.println("vertaling caat: "+translate.TranslateEngels(cat.getCategory()));
                        System.out.println("get con " + cat.getConfidence());
/*vertaal naar nl*/     category.appendText(cat.getCategory() +" ");       //translate.TranslateEngels(
                    } else {
                        System.out.println("leeg");
                    }
                }
                for (Category category : categories) {
                    Float o = category.getConfidence() * 100;
                    String b = o.toString();
                    confidence.appendText("%" + String.valueOf(Math.round(Float.parseFloat(b)))+" ");
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

//            Map<String, Float> mmap = new HashMap<>();
//            if (enList != null) {
//                for (Entity i : enList) mmap.put(i.getName(), i.getSalience());
//            }

            for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                Float omzettenFloat = sal.getValue() * 100;
                String afrondenFloat = omzettenFloat.toString();

/*entities*/    entities.appendText(sal.getKey() + " , " +sal.getValue()+ "\n");          //translate.TranslateEngels(

                salience.appendText("%" + Math.round(Float.parseFloat(afrondenFloat)) + " , ");
            }

////  Subject fix
            Map<String, Float> map = new HashMap<>();
            TranslateText translateText = new TranslateText();
            for (Entity hash : enList) {
                String s = translateText.TransIt(hash.getName()); //, hash.getSalience());
//                map.put(s, hash.getSalience());    //originele code
                map.put(hash.getName(), hash.getSalience());
//                System.out.println("serkan");
//                System.out.println(s);
//                System.out.println("after serkan");
            }

            List<Float> salien = new ArrayList<>();

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
/*Subject*/         subject.appendText(print.getKey());   //translate.TranslateEngels(
                } else {
                    System.out.println("error hashmap");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        newButton.setOnAction(
                e -> {
                    test1.clear();
                    test2.clear();
                    test3.clear();
                    List<File> list =
                            fileChooser.showOpenMultipleDialog(newButton.getScene().getWindow());

                    //TranslateText translateText = new TranslateText();
                    Analyze_Entities analyze = new Analyze_Entities();
                    //Apacke_Tika_Docx docx = new Apacke_Tika_Docx();
                    TypeDetection typeDetection = new TypeDetection();

                    //, hash.getSalience());
//                map.put(s, hash.getSalience());    //originele code
                    // map.put(hash.getName(), hash.getSalience());

                    int count = 0;
                    for (File i : list) {
                        //System.out.println(((File) i).getAbsolutePath());
                        System.out.println(i.getAbsolutePath());

                       // if (i instanceof File) {
                        //newfile = (File) i;
                        //System.out.println(newfile.getAbsolutePath());
                            count++;
                            System.out.println(count);

                            try {

                                List<Entity> entities = analyze.analyzeEntities(typeDetection.getFile(i));

                                String ta = "test"+String.valueOf(count);
                                System.out.println(ta);
                                    for (Entity s : entities) {
                                        System.out.println(s.getName());
                                         if (findArea(ta) != null) {
                                        Float m = Float.valueOf(Math.round(s.getSalience() * 100));
                                       // test3.appendText("Entity: "+s.getName()+", Salience: "+m+"\n");
                                        TextArea temp = findArea(ta);
                                        temp.appendText("Entity: "+s.getName()+", Salience: "+m+"\n");
                                         }
                                    }
//                                    if (test1.getText().isEmpty()) {
//                                        test1.appendText(s.toString());
//
//                                    } else if (!test1.getText().isEmpty() && test2.getText().isEmpty()) {
//                                        test2.appendText(s.toString());
//
//                                    } else if (!test2.getText().isEmpty() && test3.getText().isEmpty()) {
//                                        test3.appendText(s.toString());
//                                    }

                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                    }
                });
    }

    private TextArea findArea(String area) {
        List<TextArea> areas = new ArrayList<TextArea>() {{
            add(test1);
            add(test2);
            add(test3);
        }};

        TextArea temp1 = new TextArea();

        for (TextArea a : areas) {
            if (a != null) {
                if (a.getId().equals(area)) {
                    temp1 = a;
                }
            }
        }
        return temp1;
    }
}



