package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

import static sample.ClientController.rectangles;

public class Hover implements Runnable {

    Map<Rectangle, String> rectangleBookMap;
    @FXML
    TilePane tilePane;
    Hover(Map<Rectangle, String> rectangleBookMap, TilePane tilePane){
        this.rectangleBookMap=rectangleBookMap;

        this.tilePane = tilePane;

    }
    @Override
    public void run() {
        while (true){

            for(int a = 0; a<rectangles.size();a++) {
                Rectangle rectangle = rectangles.get(a);

                    String bookConf=rectangleBookMap.get(a);

                    double Xofrect=rectangle.getX();
                    double Yofrect=rectangle.getY();
//                    String param = parseJSON(bookConf, "years");
                    Text text=new Text();
                    text.setText("1");
                    text.setX(Xofrect);
                    text.setY(Yofrect);
                    tilePane.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.printf("coordinate X: %.2f, coordinate Y: %.2f\n",event.getX(),event.getY());
                            System.out.println(event.getSource());

                            if (event.getSource() instanceof Rectangle) {
                                for (Rectangle r:rectangles
                                     ) {
                                    r.setFill(Color.BLUE);

                                }
                            } else rectangle.setFill(Color.INDIANRED);
                        }

                        });

        }
    }
    }}
