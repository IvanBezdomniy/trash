package lab.Server;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Map;

import static lab.Server.Collect.*;
import static lab.Server.CommandsPatterns.checkWithRegExpImport;
import static lab.Server.CommandsPatterns.checkWithRegExpInsert;
import static lab.Server.CommandsPatterns.checkWithRegExpRemove;

public class ConsoleReader implements Runnable {
    String recieve;
    DatagramChannel channel;
    SocketAddress adr;
    Collect all;

    public ConsoleReader(String recieve, DatagramChannel channel, SocketAddress adr, Collect all) {
        this.recieve = recieve;
        this.channel = channel;
        this.adr = adr;
        this.all = all;
    }


    public static void deserialize(Collect all){
        try {
            all.deserializeFromXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        System.out.println("2");
        String sendData = "";

        if ("json".equals(recieve)){
            recieve=recieve.substring(5);
            Integer a = Integer.valueOf(recieve);
            try {
                sendData=ObjectToJson(a);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        if("add".equals(recieve)){
            all.Add();
        }
        if ("save".equals(recieve)) {
            save();
            sendData="Коллекция сохранена";
        }
        if (checkWithRegExpRemove(recieve) == true) {
            System.out.println("Работает");
            int l = recieve.length();
            String format_input;
            format_input = recieve.substring(16, l - 1);
            sendData=(format_input);

        }
        if (checkWithRegExpImport(recieve) == true) {
            int l = recieve.length();
            String format_input;
            format_input = recieve.substring(8, l - 1);
            try {
                all.Import(format_input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ("1".equals(recieve)) {
            String send = "";

            for (Integer key : Liblaries.keySet()) {
                send = send + ((Liblaries.get(key)).toString())+"\n";

            }
            sendData = send;
        }
        if("count".equals(recieve)){
            Integer a=(int) Liblaries.entrySet().stream().count();
            sendData = a.toString();
        }

        if (checkWithRegExpInsert(recieve) == true) {
            int l = recieve.length();
            Integer format_key;
            String format_value;
            format_key = Integer.parseInt(recieve.substring(8, recieve.indexOf("}")));
            format_value = recieve.substring(recieve.indexOf("}") + 3, l - 1);
            JsonToObject(format_value, format_key);
            sendData="key: " + format_key + " " + "value: " + format_value;
        }
        System.out.println(sendData);
        ByteBuffer outBuf = ByteBuffer.wrap(sendData.getBytes());
        try {
            channel.send(outBuf,adr);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}



