package lab.Server;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;

public class WorkWithClients implements Runnable
{
    private DatagramChannel serverSocket;
    private Collect all ;

    public WorkWithClients(DatagramChannel serverSocket, Collect all)
    {
        this.serverSocket = serverSocket;
        this.all = all;
    }

    @Override
    public void run()
    {
        try
        {

            while (true)
            {
                System.out.println("Ready to serve clients");

                ByteBuffer inBuf = ByteBuffer.wrap(new byte[64*1024]);
                SocketAddress clientAdr = serverSocket.receive(inBuf);

                Thread clientService = new Thread(new ServOneClient(clientAdr, serverSocket, inBuf, all));
                clientService.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                serverSocket.close();
            } catch (IOException e)
            {
                System.err.println("Server socket isn't closed");
                e.printStackTrace();
            }
        }
    }

}

