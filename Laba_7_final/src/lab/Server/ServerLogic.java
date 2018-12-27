package lab.Server;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static lab.Server.ConsoleReader.deserialize;

public class ServerLogic {

    public static void main(String[] args) throws IOException {

        Collect all = new Collect();
        deserialize(all);
        DatagramChannel serverSocket = DatagramChannel.open();

        SocketAddress address = new InetSocketAddress(8081);
        serverSocket.bind(address);

        Thread workWithClients = new Thread(new WorkWithClients(serverSocket, all));
        workWithClients.start();


    }
}

//    public static void main(String args[]) throws Exception{
//        serverLogic=new ServerLogic();
//        Thread thread = new Thread(serverLogic);
//        thread.start();
//}


