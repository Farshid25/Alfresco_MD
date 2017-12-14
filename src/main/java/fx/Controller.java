package fx;

import de.l3s.boilerpipe.document.TextDocument;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Category;
import org.apache.pdfbox.debugger.ui.DocumentEntry;
import org.apache.tika.detect.TextDetector;
import services.NaturalLangugageApi.Classifying_Content;
import services.Type_Read_Switch.TypeDetection;
import services.content_reader.Apacke_Tika_Docx;

import javax.print.attribute.standard.DocumentName;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    TypeDetection typeDetection = new TypeDetection();
    Apacke_Tika_Docx apacheDoc = new Apacke_Tika_Docx();
    Classifying_Content cc = new Classifying_Content();
    Category cat = new Category();

    @FXML
    private Button btn1;

    @FXML
    public TextArea showText;

    @FXML
    private Label lbl1;

    @FXML
    private TextField txtField1;  @FXML private TextField txtField2;

    @FXML
    public FileChooser fileChooser = new FileChooser();
    //    @FXML
//    private DocumentName documentName = new DocumentName();    //("dd","src\\main\\Aanvullende_Files\\test1.docx");
//
//    @FXML
//    private TextDocument textDocument = new TextDocument();
//
    @FXML
    TextDetector textDetector = new TextDetector();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn1.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(btn1.getScene().getWindow());

            try {
                cc.classifyFile(apacheDoc.readFile(file.getAbsolutePath()), cat);
                if (cat.getCategory() != null) {
                    txtField1.appendText(cat.getCategory());
                    //txtField2.appendText(cat.getConfidence().toString())

                    String o = cat.getConfidence().toString();
                    txtField2.appendText(o);
                    //System.out.println(String.valueOf(cat.getConfidence()));
                }
                else{
                    System.out.println("error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                txtField1.appendText(typeDetection.mySwitch(file.getAbsolutePath()));
//                showText.appendText("\nYour File Name is: "+file.getName());
//                //showText.appendText(typeDetection.mySwitch(file.getAbsolutePath()));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        });
    }

//
//    @FXML
//    private Button eenAfbeelding;
//
//    @FXML
//    private Button meerdereAfbeeldingen;
//
//    @FXML
//    private final FileChooser fileChooser = new FileChooser();
//
//    @FXML
//    private final DirectoryChooser directoryChooser = new DirectoryChooser();
//
//    @FXML
//    private ImageMetadata imageMetadata = new ImageMetadata();
//
//    private SchedulerFactory schedFact = new StdSchedulerFactory();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        eenAfbeelding.setOnAction(
//                e -> {
//                    File file = fileChooser.showOpenDialog(eenAfbeelding.getScene().getWindow());
//                    if (file != null) {
//                        showText.clear();
//                        try {
//                            showText.appendText(imageMetadata.showImageMetadata(file));
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                });
//
//        meerdereAfbeeldingen.setOnAction(
//                e -> {
//                    List list =
//                            fileChooser.showOpenMultipleDialog(meerdereAfbeeldingen.getScene().getWindow());
//                    if (list != null) {
//                        showText.clear();
//                        for (Object file : list) {
//                            if (file instanceof File) {
//                                File newFile = (File) file;
//                                try {
//                                    showText.appendText(imageMetadata.showImageMetadata(newFile));
//                                } catch (Exception e1) {
//                                    e1.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                });

}