package lab.Server;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ObjectController {
    @FXML
    TextField textObject;
    @FXML
    private Button submit;
    @FXML
    void SubmitObjectbyEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            submitObject();}
    }
    @FXML
    void submitObject() {
        String s = textObject.getText();
        System.out.println(s);
        DoCommands.command = DoCommands.command+s;
        DoCommands.DoCommand();

    }

}
