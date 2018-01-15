package fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.*;
import java.lang.*;
import java.util.List;

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
    public TextArea file1;
    @FXML
    public TextArea file2;
    @FXML
    public TextArea file3;
    @FXML
    public TextArea file1Category;
    @FXML
    private TextArea file1Subject;
    @FXML
    public TextArea file2Category;
    @FXML
    private TextArea file2Subject;
    @FXML
    public TextArea file3Category;
    @FXML
    private TextArea file3Subject;
    @FXML
    private TextArea category;
    @FXML
    TextField fileName;
    @FXML
    TextArea fileNames;
    @FXML
    private TextField nextCategorie;
    @FXML
    private TextArea multiSuitable;
    @FXML
    private TextField subject;
    @FXML
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        text.setText("Incentro");
//        text.setFont(Font.font(null, FontWeight.BOLD, 60));
//        text.setFill(Color.ORANGE);

        btn1.setOnAction(e -> {
            category.clear();
            fileNames.clear();
            subject.clear();
            entities.clear();
            fileName.clear();
            nextCategorie.clear();

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
                for (Map.Entry<String, Float> mm : cattt.entrySet()) {
                    test.add(mm.getValue());
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
                    System.out.println(print.getKey() + print.getValue());
                    nextCategorie.appendText(print.getKey());
                }
            } catch (Exception b) {
                b.printStackTrace();
            }

// Entities & Saliences fix

            List<Category> categories = null;
            TranslateText translate = new TranslateText();
            try {
                categories = cc.classifyFile(typeDetection.getFile(file));   //file.getAbsolutePath()));
                int count = 0;
                boolean check = false;

                for (Map.Entry<String, Float> dd : cattt.entrySet()) {
                    if (dd.getKey() != null) {
                        float f = dd.getValue() * 100;
                        int rounded = Math.round(f);
                        String ss = String.valueOf(rounded);
                        count += 1;
                        if (rounded >= 80) {
                            check = true;
                        }
//                        System.out.println("category " + cat.getCategory());
///*vertaal naar nl*/
//                        System.out.println("vertaling tat: " + translate.TranslateEngels(cat.getCategory()));
//                        System.out.println("confidence " + cat.getConfidence());
/*vertaal naar nl*/
                        category.appendText(count + ": " + "[" + dd.getKey() + "] " + "%" + ss + "\n");          //translate.TranslateEngels(
                    } else {  //entities.appendText(sal.getKey() + " , " + "%" + sali + "\n");
                        System.out.println("leeg");
                    }
                }
                if (check) {
                    category.setStyle("-fx-control-inner-background:#4cf064");
                    category.appendText("\nWordt gemetadateerd, want er zit 1 of meer confidence boven 80");
                } else {
                    category.setStyle("-fx-control-inner-background:#b60100");
                    category.appendText("\nWordt niet gemetadateerd");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
//_______________________________________________________________________________________________________________________________________________

            Map<String, Float> fifi = new HashMap<>();

            assert categories != null;
            for (Category category : categories) {
                fifi.put(category.getCategory(), category.getConfidence());
            }
//_______________________________________________________________________________________________________________________________________________

            for (Map.Entry<String, Float> sal : defeaultMap.entrySet()) {
                Float omzettenFloat = sal.getValue() * 100;
                String afrondenFloat = omzettenFloat.toString();
                int sali = Math.round(Float.parseFloat(afrondenFloat));

/*entities*/
                entities.appendText(sal.getKey() + " , " + "%" + sali + "\n");          //translate.TranslateEngels(
            }

//Subject fix
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
// NEW BUTTON _______________________________________________________________________________________________________________________________________________________________
        newButton.setOnAction(
                e -> {
                    file1.clear();
                    file2.clear();
                    file3.clear();
                    file1Category.clear();
                    file1Subject.clear();
                    file2Category.clear();
                    file2Subject.clear();
                    file3Category.clear();
                    file3Subject.clear();

                    List<File> list =
                            fileChooser.showOpenMultipleDialog(newButton.getScene().getWindow());
                    Analyze_Entities analyze = new Analyze_Entities();
                    Classifying_Content cc = new Classifying_Content();
                    TypeDetection typeDetection = new TypeDetection();

                    int count = 0;
                    for (File i : list) {
                        count++;
                        fileNames.appendText(i.getName() + "\n");
                        Map<String, Float> multiMap = new HashMap<>();
                        List<Entity> entities = null;
                        List<Category> categories = new ArrayList<>();

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

                        for (Map.Entry<String, Float> mmm : multiMap.entrySet()) {
                            String ta = "file" + String.valueOf(count);
                            float f = mmm.getValue() * 100;
                            int rounded = Math.round(f);
                            String ss = String.valueOf(rounded);
                            if (findArea(ta) != null) {
                                TextArea temp = findArea(ta);
                                temp.appendText("Entity: " + mmm.getKey() + " %" + ss + "\n");
                            }
                        }
                        List<Float> salien = new ArrayList<>();

                        for (Map.Entry<String, Float> sal : multiMap.entrySet()) {
                            salien.add(sal.getValue());
                        }

                        float max = 0;

                        try {
                            max += Collections.max(salien);

                            Map.Entry<String, Float> print = null;

                            for (Map.Entry<String, Float> en : multiMap.entrySet()) {
                                if (en.getValue().equals(max)) {
                                    print = en;
                                    System.out.println("test 0" + print.getKey());
                                }
                            }

                            String dubbelSubject1 = "";
                            String dubbelSubject2 = "";
                            String dubbelSubject3 = "";
                            for (Category category : categories) {
                                float f = category.getConfidence() * 100;
                                int rounded = Math.round(f);
                                String ss = String.valueOf(rounded);
                                if (count == 1 && print != null) {
                                    file1Category.appendText("[" + category.getCategory() + "]" + " %" + ss + "\n");
                                    if (rounded >= 80) {
                                        multiSuitable.appendText(i.getName() + "\n");
                                        file1Category.setStyle("-fx-control-inner-background:#4cf064");
//                                        category.appendText("\nWordt gemetadateerd, want er zit 1 of meer confidence boven 80");
                                    }
                                    dubbelSubject1 = print.getKey();

                                } else if (count == 2 && print != null) {
                                    file2Category.appendText("[" + category.getCategory() + "]" + " %" + ss + "\n");
                                    dubbelSubject2 = print.getKey();
                                    if (rounded >= 80) {
                                        multiSuitable.appendText(i.getName() + "\n");
                                        file2Category.setStyle("-fx-control-inner-background:#4cf064");
                                    }
                                    dubbelSubject2 = print.getKey();

                                } else if (count == 3 && print != null) {
                                    file3Category.appendText("[" + category.getCategory() + "]" + " %" + ss + "\n");
                                    if (rounded >= 80) {
                                        multiSuitable.appendText(i.getName() + "\n");
                                        System.out.println("zest 3" + print.getKey());
                                    }
                                    dubbelSubject3 = print.getKey();
                                    break;
                                }
                            }
                            file1Subject.appendText(dubbelSubject1);
                            file2Subject.appendText(dubbelSubject2);
                            file3Subject.appendText(dubbelSubject3);


                        } catch (Exception p) {
                            p.printStackTrace();
                        }


//                        if (file3Category.getText().equals("")){
//
//                            file3Category.appendText("farshid test kir");
//                        }
//                        System.out.println(file3Category.getText() + "FARSHID");
                    }
                });
    }


    private TextArea findArea(String area) {
        List<TextArea> areas = new ArrayList<TextArea>() {{
            add(file1);
            add(file2);
            add(file3);
            add(file1Category);
            add(file2Category);
            add(file3Category);
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







