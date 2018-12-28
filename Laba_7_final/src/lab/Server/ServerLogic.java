package lab.Server;

import lab.Server.ORM.Attribute;
import lab.Server.ORM.SQLCommands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static lab.Server.ConsoleReader.deserialize;

public class ServerLogic {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "12345";
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


//sqlcom.createWithDependencies(
//        System.out.println(sqlcom.select(new ArrayList<>()) + ";");
//        System.out.println(sqlcom.delete(kek));



    }
    public static void toDB(String json) {
        SQLCommands sqlcom = new SQLCommands(Book.class);

        System.out.println(json);

        try {


            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        }catch (Exception e){
            System.err.println("Не удалось загрузить драйвер");
            e.printStackTrace();
        }
        try {
            Connection connection=DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = connection.createStatement();
            if (json.substring(json.length()-1).equals("1")) {

                System.out.println("dadadad");
                json=json.substring(0,json.length()-1);
                statement.executeQuery(sqlcom.insert(json));

            }


        } catch (Exception e) {
            System.err.println("Не удалось подключиться");
            e.printStackTrace();
        }

    }
}

//    public static void main(String args[]) throws Exception{
//        serverLogic=new ServerLogic();
//        Thread thread = new Thread(serverLogic);
//        thread.start();
//}


