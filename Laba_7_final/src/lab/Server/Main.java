package lab.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.PriorityBlockingQueue;

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

