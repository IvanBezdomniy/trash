package sample;

import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

import static sample.ClientController.rectangles;

public class Hover implements Runnable {

    Map<Rectangle, String> rectangleBookMap;
    Hover(HashMap<Rectangle, String> rectangleBookMap){
        this.rectangleBookMap=rectangleBookMap;

    }
    @Override
    public void run() {
        rectangles.size();
    }
}
