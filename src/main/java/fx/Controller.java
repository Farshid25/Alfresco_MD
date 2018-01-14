package fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Category;
import model.Entity;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import services.GoogleTranslationApi.TranslateText;
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
    private AnchorPane anchorPane;
    @FXML
    private Button btn1;
    @FXML
    private Button newButton;
    @FXML
    public TextArea entities;
    @FXML
    public TextArea test1;
    @FXML
    public TextArea test2;
    @FXML
    public TextArea test3;
    @FXML
    public TextArea finalTxt1;
    @FXML
    public TextArea finalTxt2;
    @FXML
    public TextArea finalTxt3;
    @FXML
    private TextField category;
    @FXML
    TextField fileName;
    @FXML
    TextField superCategorie;
    @FXML
    private TextField confidence;
    @FXML
    private TextField subject;
    @FXML
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anchorPane.setStyle("-fx-background-color: #FFB252;");
        btn1.setOnAction(e -> {
            category.clear();
            confidence.clear();
            subject.clear();
            entities.clear();
            fileName.clear();
            superCategorie.clear();

            File file = fileChooser.showOpenDialog(btn1.getScene().getWindow());
            fileName.appendText(file.getName());

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

            // Superieure CATEGORIE eruit halen.
            List<Float> test = new ArrayList<>();
            Map<String, Float> cattt = new HashMap<>();
            try {
                List<Category> testCategorie = cc.classifyFile(typeDetection.getFile(file));
                for (Category c : testCategorie) {
                    cattt.put(c.getCategory(), c.getConfidence());
                }
                for (Map.Entry<String, Float> pp : cattt.entrySet()) {

                    test.add(pp.getValue());
                    System.out.println("Eerst");
                    System.out.println(pp.getKey() + " " + pp.getValue());
                    System.out.println("daarna");
                    //fileName.appendText(pp.getKey());
                }
            } catch (TikaException e1) {
                e1.printStackTrace();
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            float maxx = 0;

            try {
                maxx += Collections.max(test);
                Map.Entry<String, Float> print = null;

                for (Map.Entry<String, Float> en : cattt.entrySet()) {
                    if (en.getValue().equals(maxx)) {
                        print = en;
                    }
                }
                if (print != null) {
                    superCategorie.appendText(print.getKey());
                }
            } catch (Exception b) {
                b.printStackTrace();
            }

// Entities & Saliences fix


            List<Category> categories = null;
            TranslateText translate = new TranslateText();
            try {
                categories = cc.classifyFile(typeDetection.getFile(file));   //file.getAbsolutePath()));

                for (Category cat : categories) {
                    if (category != null) {
                        System.out.println("get cat " + cat.getCategory());
/*vertaal naar nl*/
                        System.out.println("vertaling caat: " + translate.TranslateEngels(cat.getCategory()));
                        System.out.println("get con " + cat.getConfidence());
/*vertaal naar nl*/
                        category.appendText(cat.getCategory() +" %"+ cat.getConfidence()+"--" +"\n");       //translate.TranslateEngels(
                    } else {
                        System.out.println("leeg");
                    }
                }
//                ________________________________________________________________________________________  Ga hier mee verder.....

            } catch (Exception e1) {
                e1.printStackTrace();
            }

//                ____________________

            List<Float> ff = new ArrayList<>();
            Map<String, Float> fifi = new HashMap<>();

            assert categories != null;
            for (Category category : categories) {
                fifi.put(category.getCategory(), category.getConfidence());
            }
            for (Map.Entry<String, Float> tt : fifi.entrySet()) {
                ff.add(tt.getValue());
                System.out.println("value added to FF list" + ff);
            }
// hier is naar de klote
            Map.Entry<String, Float> deMap = null;
            float listMax = Collections.max(ff);
            try {
                System.out.println("listmax " + listMax);

                for (Map.Entry<String, Float> rr : fifi.entrySet()) {
                    if (rr.getValue().equals(listMax)) {
                        deMap = rr;

                        System.out.println(rr.getValue());
                    } else {
                        System.out.println("tjar");
                    }
                }
            } catch (Exception raar) {
                raar.printStackTrace();
            }
            String gek = "";
//            for (Map.Entry<String, Float> nn : {                                 // hier mee verderrrrrrrrrr 13-1-18

            boolean check = false;

            for (Map.Entry<String, Float> l : fifi.entrySet()) {

                float fl = l.getValue() * 100;
                int rounded = Math.round(fl);
                confidence.appendText(String.valueOf(rounded)+" ");
                if (rounded >= 80) {
                    check = true;

                }
            }

            if (check) {
                confidence.setStyle("-fx-control-inner-background:#4cf064");
//                confidence.setba
            }else{
                confidence.setStyle("-fx-control-inner-background:#ff545e");
            }


//            if (Math.round(deMap.getValue()) >= 80) {
//                System.out.println("ooooo is goedd");
//                confidence.setStyle("-fx-control-inner-background:#41f226");
//                confidence.appendText(String.valueOf(Math.round(Float.parseFloat(gek))));
//                //onfidence.appendText("%" + String.valueOf(Math.round(Float.parseFloat(b))) + " ");
//            } else {
//                System.out.println("oooo Failed");
//                confidence.setStyle("-fx-control-inner-background:#FF3333");
//                confidence.appendText(gek);
//                // confidence.appendText("%" + String.valueOf(Math.round(Float.parseFloat(b))) + " ");
//            }


//            Map<String, Float> mmap = new HashMap<>();
//            if (enList != null) {
//                for (Entity i : enList) mmap.put(i.getName(), i.getSalience());
//            }

            for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                Float omzettenFloat = sal.getValue() * 100;
                String afrondenFloat = omzettenFloat.toString();
                int sali = Math.round(Float.parseFloat(afrondenFloat));

/*entities*/
                entities.appendText(sal.getKey() + " , " + "%" + sali + "\n");          //translate.TranslateEngels(

                //salience.appendText("%" + Math.round(Float.parseFloat(afrondenFloat)) + " , ");
            }

////  Subject fix
            TranslateText translateText = new TranslateText();
            List<Float> salien = new ArrayList<>();

            for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                salien.add(sal.getValue());
            }

            float max = 0;

            try {
                max += Collections.max(salien);

                Map.Entry<String, Float> print = null;

                for (Map.Entry<String, Float> en : defeaultMap.entrySet()) {
                    if (en.getValue().equals(max)) {
                        print = en;
                    }
                }
                if (print != null) {

                    subject.appendText(print.getKey());   //translate.TranslateEngels(
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
                        finalTxt1.clear();
                        finalTxt2.clear();
                        finalTxt3.clear();

                        List<File> list =
                                fileChooser.showOpenMultipleDialog(newButton.getScene().getWindow());
//File f = (File) fileChooser.showOpenMultipleDialog(newButton.getScene().getWindow());
                        //TranslateText translateText = new TranslateText();
                        Analyze_Entities analyze = new Analyze_Entities();
                        Classifying_Content cc = new Classifying_Content();
                        TypeDetection typeDetection = new TypeDetection();


                        int count = 0;
                        for (File i : list) {
                            Map<String, Float> multiMap = new HashMap<>();
                            List<Entity> entities = null;
                            List<Category> categories = new ArrayList<>();
                            count++;
                            //System.out.println(count);
                            System.out.println(i.getAbsolutePath());
                            try {
                                entities = analyze.analyzeEntities(typeDetection.getFile(i));
                                categories = cc.classifyFile(typeDetection.getFile(i));

                                for (Entity multi : entities) {

                                    multiMap.put(multi.getName(), multi.getSalience());
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }

                            try {

                                //finalTxt1.appendText(category.getCategory() + " " + category.getConfidence() + "\n");

                                String test = null;

//                                test += i.getName().toString();  //="farshid";
                                System.out.println("FARSHID");
//                                System.out.println(test);
                                System.out.println(count);
                                System.out.println("FARSHOD");

                                for (Map.Entry<String, Float> mmm : multiMap.entrySet()) {
                                    String ta = "test" + String.valueOf(count);

                                    if (findArea(ta) != null) {
                                        Float m = (float) Math.round(mmm.getValue() * 100);
                                        // test3.appendText("Entity: "+s.getName()+", Salience: "+m+"\n");
                                        TextArea temp = findArea(ta);
                                        temp.appendText("Entity: " + mmm.getKey() + ", Salience: " + m + "\n");  //normal s.getName van for loop
                                    }
                                }
                                for (Category category : categories) {
                                    if (count == 1) {
                                        finalTxt1.appendText(category.getCategory() + " " + category.getConfidence() + "\n");
                                    } else if (count == 2) {
                                        finalTxt2.appendText(category.getCategory() + " " + category.getConfidence() + "\n");
                                    } else if (count == 3) {
                                        finalTxt3.appendText(category.getCategory() + " " + category.getConfidence() + "\n");
                                    } else {
                                        System.out.println("append error");
                                    }
                                }

                            } catch (Exception ff) {
                                ff.printStackTrace();
                            }
                        }
                    });
        }

    private TextArea findArea(String area) {
        List<TextArea> areas = new ArrayList<TextArea>() {{
            add(test1);
            add(test2);
            add(test3);
            add(finalTxt1);
            add(finalTxt2);
            add(finalTxt3);
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







