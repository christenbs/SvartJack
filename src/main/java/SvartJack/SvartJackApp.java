package SvartJack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SvartJackApp extends Application {
    

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("SvartJack");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SvartJack/App.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root, 2560, 1600);

        scene.getStylesheets().add(getClass().getResource("/SvartJack/style/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}