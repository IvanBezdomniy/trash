package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("password.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 677, 369));
        primaryStage.show();
        primaryStage.setMaxHeight(369);
        primaryStage.setMaxWidth(679);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
