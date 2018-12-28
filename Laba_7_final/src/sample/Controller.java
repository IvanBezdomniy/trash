package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    final  String password1 = "123";
    final String login1 ="Vano";
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private Button auth;

    @FXML
    private TextField login;



    @FXML
    public void onEnter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            authentification();
        }
    }
    @FXML
    public void onPress(ActionEvent a){
        authentification();
    }

    void authentification(){
//        if(password.getText().equals(password1) && login.getText().equals(login1)) {
//            System.out.println("Amazing!!!");
            Stage stage = (Stage) auth.getScene().getWindow();
            // do what you have to do
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Другая форма");
            stage.setScene(new Scene(root1, 1213, 700, true, SceneAntialiasing.BALANCED));
            stage.show();
//
//        }
//        else System.out.println("Not cool...");

    }
    

    @FXML
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'password.fxml'.";
        assert auth != null : "fx:id=\"auth\" was not injected: check your FXML file 'password.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'password.fxml'.";


    }
}
