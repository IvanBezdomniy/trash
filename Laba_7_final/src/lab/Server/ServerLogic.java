package lab.Server;

import lab.Server.ORM.Attribute;
import lab.Server.ORM.SQLCommands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        Attribute attr1 = new Attribute("pages");
        Attribute attr2 = new Attribute("years");
        Map<Attribute, String> kek = new HashMap<>();
        kek.put(attr1,"100");


        SQLCommands sqlcom = new SQLCommands(Book.class);
        System.out.println(sqlcom.createWithDependencies());
        System.out.println(sqlcom.select(new ArrayList<>()) + ";");
        System.out.println(sqlcom.delete(kek));



    }
}

//    public static void main(String args[]) throws Exception{
//        serverLogic=new ServerLogic();
//        Thread thread = new Thread(serverLogic);
//        thread.start();
//}


