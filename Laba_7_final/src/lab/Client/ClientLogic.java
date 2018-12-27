package lab.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ClientLogic {
    static int port = 8081;
    static String books;
    public static void main(String[] args)
{
    Scanner in = new Scanner(System.in);
    while (true)
    {
        System.out.println("Enter a command:");

        try
        {
            Thread workWithServer = new Thread(new WorkWithServer(port, in.nextLine()));
            workWithServer.start();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            System.exit(0);
        }

    }
}


    public static String request(String message) throws IOException {
        WorkWithServer workWithServer = new WorkWithServer(port, message);
        return workWithServer.doRequest();

    }

}