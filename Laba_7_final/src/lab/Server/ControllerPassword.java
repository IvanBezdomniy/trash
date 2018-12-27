package lab.Server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerPassword {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    TreeView<String> treeView;

    @FXML
    private PasswordField password;

    @FXML
    private Button submit;
    public void onEnter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            authentification();
        }
    }
    @FXML
    public void onPress(ActionEvent a){
        authentification();
    }
    String passwordright = "password";

    void authentification(){
        if(password.getText().equals(passwordright)){
            System.out.println("Верно");
            try {
                Stage stage = (Stage) submit.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Fxml/Main.fxml"));
                stage2.setTitle("Main");
                stage2.setResizable(false);
                stage2.setScene(new Scene(root));
                stage2.show();
            }

            catch( Exception e){}
            }

        else System.out.println("Неверно");}

    @FXML
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'passwordnew.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'passwordnew.fxml'.";

    }}