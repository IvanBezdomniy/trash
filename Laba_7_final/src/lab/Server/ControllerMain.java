package lab.Server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button Remove_button;

    @FXML
    private Button Save_button;

    @FXML
    private Button Clear_button;

    @FXML
    private Button Add_button;

    @FXML
    private Button Remove_greater_button;

    @FXML
    void initialize() {

        assert Remove_button != null : "fx:id=\"Remove_button\" was not injected: check your FXML file 'Main.fxml'.";
        assert Save_button != null : "fx:id=\"Save_button\" was not injected: check your FXML file 'Main.fxml'.";
        assert Clear_button != null : "fx:id=\"Clear_button\" was not injected: check your FXML file 'Main.fxml'.";
        assert Add_button != null : "fx:id=\"Add_if_min_button\" was not injected: check your FXML file 'Main.fxml'.";


    }

    public void addButton(ActionEvent actionEvent) {
        System.out.println("addButton");
        DoCommands.command = "add";
        showObjectInput(actionEvent);
    }



    public void clearButton(ActionEvent actionEvent) {
        System.out.println("clear");
        DoCommands.command = "clear";
        DoCommands.DoCommand();
    }

    public void removeButton(ActionEvent actionEvent) {
        System.out.println("remove");
        DoCommands.command = "remove ";
        showObjectInput(actionEvent);

    }

    public void saveButton(ActionEvent actionEvent) {
        System.out.println("save");
        DoCommands.command = "save";
        DoCommands.DoCommand();
    }


    public void showObjectInput(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Fxml/Object.fxml"));
            stage.setTitle("Ввод объекта");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        }
        catch (IOException e) {e.printStackTrace();}
    }
}
