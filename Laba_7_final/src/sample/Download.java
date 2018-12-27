package sample;

import lab.Client.ClientLogic;

import java.io.IOException;
import static sample.ClientController.jsonbooks;
public class Download implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                jsonbooks= ClientLogic.request("1");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
