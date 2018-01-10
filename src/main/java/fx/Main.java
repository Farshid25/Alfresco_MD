package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml.fxml"));
        primaryStage.setTitle("Metadateren ");
        primaryStage.setScene(new Scene(root, 1280, 980));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}