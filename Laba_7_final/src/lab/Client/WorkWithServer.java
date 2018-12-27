package lab.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WorkWithServer implements Runnable
{
    private DatagramSocket socket;
    private InetSocketAddress address;
    private int timeout = 2000;
    private String message;

    public WorkWithServer(int port, String message) throws IOException
    {
        address = new InetSocketAddress("localhost",port);

        socket = new DatagramSocket();
        socket.setSoTimeout(timeout);

        this.message = message;

    }

    public String doRequest(){
        try
        {
            byte[] outBytes = message.getBytes();
            DatagramPacket packet = new DatagramPacket(outBytes, outBytes.length, address);

            socket.send(packet);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {

            byte[] inBytes = new byte[1024*64];

            DatagramPacket packet = new DatagramPacket(inBytes, inBytes.length);

            socket.receive(packet);

            String data = Arrays.toString(packet.getData());
            String str = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println(str + " from server");
            return str;

        }
        catch (SocketTimeoutException e)
        {
            System.out.println("Sorry "+e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        socket.close();
        return "error";
    }



    @Override
    public void run()
    {
        try
        {
            byte[] outBytes = message.getBytes();
            DatagramPacket packet = new DatagramPacket(outBytes, outBytes.length, address);

            socket.send(packet);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {

            byte[] inBytes = new byte[1024*64];

            DatagramPacket packet = new DatagramPacket(inBytes, inBytes.length);

            socket.receive(packet);

            String data = Arrays.toString(packet.getData());
            String str = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            System.out.println(str + " from server");


        }
        catch (SocketTimeoutException e)
        {
            System.out.println("Sorry "+e.getMessage());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        socket.close();

    }


}
