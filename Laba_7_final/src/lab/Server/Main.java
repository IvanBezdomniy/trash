package lab.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("Fxml/passwordnew.fxml"));
            primaryStage.setTitle("Вход");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }


    public static void main(String[] args) throws JAXBException, IOException {

            ServerLogic.server();
            Collect collect = new Collect();
        System.out.println(Collect.fromObjectToJsonString(collect.getLiblaries().get(1)));
            launch(args);

      }}

