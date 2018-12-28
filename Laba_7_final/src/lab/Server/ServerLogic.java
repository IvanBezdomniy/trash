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
    static final String DB_URL = "jdbc:postgresql://localhost:5432/roma";
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

        toDB();

//sqlcom.createWithDependencies(
//        System.out.println(sqlcom.select(new ArrayList<>()) + ";");
//        System.out.println(sqlcom.delete(kek));



    }
    public static void toDB() {
        SQLCommands sqlcom = new SQLCommands(Book.class);
        try {


            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        }catch (SQLException e){
            System.err.println("Не удалось загрузить драйвер");
            e.printStackTrace();
        }
        try {
            Connection connection=DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(sqlcom.insert("1"));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlcom.select(new ArrayList<>()) + ";");

                resultSet.toString();

        } catch (SQLException e) {
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


