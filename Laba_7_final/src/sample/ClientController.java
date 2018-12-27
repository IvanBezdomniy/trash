package sample;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import lab.Client.ClientLogic;
import lab.Server.Book;
import lab.Server.Collect;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ClientController {
    public static Double filterPages = 0.0;
    public static Double filterYears = 0.0;
    public static Map<Integer, Book> books;
    public static List<Rectangle> rectangles= new ArrayList<>();
    public static String jsonbooks;
    Map<Rectangle, String> rectangleBookMap;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    List<String> listBooks = new ArrayList<>();

    @FXML
    private Slider sliderPages;

    @FXML
    private TilePane tilepane;

    @FXML
    private Slider sliderYears;

    @FXML
    private Button buttonAnimation;

    @FXML
    void initialize() throws IOException {
        assert sliderPages != null : "fx:id=\"sliderPages\" was not injected: check your FXML file 'client.fxml'.";
        assert tilepane != null : "fx:id=\"tilepane\" was not injected: check your FXML file 'client.fxml'.";
        assert sliderYears != null : "fx:id=\"sliderYears\" was not injected: check your FXML file 'client.fxml'.";
        assert buttonAnimation != null : "fx:id=\"buttonAnimation\" was not injected: check your FXML file 'client.fxml'.";

        Collect all = new Collect();
        all.Add();
//        deserialize(all);
//        books=all.getLiblaries();
        rectangleBookMap = new ConcurrentHashMap<>();
        jsonbooks= ClientLogic.request("1");
        int indexn;
        Thread downloadThread = new Thread(new Download());

        while (true){
            indexn=jsonbooks.indexOf("\n");
            if (indexn==-1)
                break;
            listBooks.add(jsonbooks.substring(0,indexn));
            jsonbooks=jsonbooks.substring(indexn+1);

        }

        sliderPages.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {
                filterPages= sliderPages.getValue();
                drawBook();
            }

        });
        sliderYears.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean wasChanging,
                    Boolean changing) {
                filterYears= sliderYears.getValue();
                drawBook();
            }
        });





    }

    private MeshView[] get3dModel() {
        ObjModelImporter objImporter = new ObjModelImporter();
        try {
            URL modelUrl = this.getClass().getResource("resource/Scooter-normals.obj");
            objImporter.read(modelUrl);
        } catch (ImportException e) {
            // handle exception
        }
        return objImporter.getImport();
    }

    public void drawBook(){
        tilepane.getChildren().clear();
        tilepane.setHgap(20);
        tilepane.setVgap(20);
        int k=0;

        for (int i=0;i<listBooks.size();i++){
            System.out.println(parseJSON(listBooks.get(i), "year"));
            if((new Double(parseJSON(listBooks.get(i), "year"))>filterYears) && (new Double(parseJSON(listBooks.get(i), "pages"))>filterPages)){
                rectangles.add(new Rectangle(50, 50, Color.INDIANRED));
                rectangleBookMap.put(rectangles.get(k), listBooks.get(i));
                tilepane.getChildren().add(rectangles.get(k));
                k++;
            }

        }
//        Thread threadHover = new Thread(new Hover(rectangleBookMap, tilepane));
//        threadHover.start();

    }




    public void clear(){
        tilepane.getChildren().clear();

    }

    public void animate(){

//        tilepane.relocate(100,0);

        KeyValue xValue = new KeyValue(tilepane.layoutXProperty(),100);
        KeyValue yValue = new KeyValue(tilepane.layoutYProperty(),0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2000), xValue, yValue);
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setOnFinished(event -> {
            for (Rectangle rect: rectangles
            ) {
                rect.setFill(Color.INDIANRED);
            }
        });
        for (Rectangle rect:rectangles) {
            rect.setFill(Color.GREENYELLOW);
        }

        timeline.play();


    }


    public static String parseJSON(String json, String par){
        int n = json.indexOf(par);
        String s = json.substring(n+par.length()+1);
        if(s.indexOf("'")!=-1)
            s= s.substring(1, s.indexOf("'"));
        else if(s.indexOf(",")!=-1)
            s=s.substring(0, s.indexOf(","));
        else
            s=s.substring(0, s.indexOf("}"));
        return s;
    }


}