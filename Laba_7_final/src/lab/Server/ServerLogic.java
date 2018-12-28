package lab.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

import static lab.Server.ConsoleReader.deserialize;

public class ServerLogic {

    public static void server() throws IOException {

        Collect all = new Collect();
        deserialize(all);
        DatagramChannel serverSocket = DatagramChannel.open();

        SocketAddress address = new InetSocketAddress(8083);
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


