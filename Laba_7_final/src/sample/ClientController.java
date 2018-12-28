package sample;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lab.Client.ClientLogic;
import lab.Server.Book;
import lab.Server.Collect;
import sample.localization.ApplicationLoc;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ClientController {
    public static Double filterPages = 0.0;
    public static Double filterYears = 0.0;
    public static Map<Integer, Book> books;
    public static List<Rectangle> rectangles= new ArrayList<>();
    public String jsonbooks;
    Map<Rectangle, String> rectangleBookMap;
    @FXML
    private ResourceBundle resources;

    @FXML
    private Menu menu;

    @FXML
    private RadioMenuItem isl;

    @FXML
    private RadioMenuItem uk;

    @FXML
    private Text textPages;

    @FXML
    private Text textYears;
    @FXML
    private RadioMenuItem lit;
    @FXML
    private RadioMenuItem rus;

    @FXML
    private URL location;

    List<String> listBooks = new ArrayList<>();
    ApplicationLoc appLoc=new ApplicationLoc();
    @FXML
    private Slider sliderPages;

    @FXML
    private TilePane tilepane;

    @FXML
    private Slider sliderYears;

    @FXML
    private Button buttonAnimation;
    int indexn;

    public int getIndexn() {
        return indexn;
    }

    public void setIndexn(int indexn) {
        this.indexn = indexn;
    }

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






//       appLoc.changeLocale(Locale.UK);
//       appLoc.changeLocale(new Locale("Russia"));
//       appLoc.changeLocale(new Locale("Island"));
//       appLoc.changeLocale(new Locale("Litov"));















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

    public void setLocaleRus(){
        if(rus.isSelected()){
            uk.setSelected(false);
            lit.setSelected(false);
            isl.setSelected(false);
            appLoc.changeLocale(new Locale("Russia"));
            textPages.setText("Страницы");
            textYears.setText("Годы");
            menu.setText("Языки");
            buttonAnimation.setText("Старт");
            System.out.println("rus");
        }
        }
    public void setLocaleUk(){
        if(uk.isSelected()){
            rus.setSelected(false);
            lit.setSelected(false);
            isl.setSelected(false);
            appLoc.changeLocale(Locale.UK);
            textPages.setText("Pages");
            textYears.setText("Years");
            menu.setText("Languages");
            buttonAnimation.setText("Start");
            System.out.println("uk");
        }
    }
    public void setLocaleLit(){
        if(lit.isSelected()){
            uk.setSelected(false);
            rus.setSelected(false);
            isl.setSelected(false);
            appLoc.changeLocale(new Locale("Litva"));
            textPages.setText("Atskaityta");
            textYears.setText("Metai");
            menu.setText("Kalbos");
            buttonAnimation.setText("Pradėti");
            System.out.println("litva");
        }
    }
    public void setLocaleIsl(){
        if(rus.isSelected()){
            uk.setSelected(false);
            lit.setSelected(false);
            rus.setSelected(false);
            appLoc.changeLocale(new Locale("Island"));
            textPages.setText("Síður");
            textYears.setText("Ár");
            menu.setText("Tungumál");
            buttonAnimation.setText("Byrja");

            System.out.println("island");
        }
    }
//        if(isl.isSelected()){
//            appLoc.changeLocale(new Locale("Island"));
//            System.out.println("Locale +" );
//        }
//        if (rus.isSelected()){
//            appLoc.changeLocale(new Locale("Russia"));
//        }
//        if(uk.isSelected()){
//            appLoc.changeLocale(Locale.UK);
//        }
//        if(lit.isSelected()){
//            appLoc.changeLocale(new Locale("Litov"));
//        }



    public void drawBook(){
        try {
            jsonbooks= ClientLogic.request("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            indexn=jsonbooks.indexOf("\n");
            if (indexn==-1)
                break;
            listBooks.add(jsonbooks.substring(0,indexn));
            jsonbooks=jsonbooks.substring(indexn+1);

        }while (jsonbooks.indexOf("\n")!=-1);
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
        listBooks.clear();
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