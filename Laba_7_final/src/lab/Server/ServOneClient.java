package lab.Server;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


public class ServOneClient implements Runnable
{
    private DatagramChannel channel;
    private SocketAddress adr;

    private ByteBuffer in;
    private Collect all;


    public ServOneClient(SocketAddress clientAdr, DatagramChannel channel, ByteBuffer inBuf, Collect all)
    {
        this.channel = channel;
        this.adr = clientAdr;
        in = inBuf;
        this.all = all;
    }

    @Override
    public void run()
    {
        //          String data = new String(in.array(), 0, , StandardCharsets.UTF_8
        String data = new String(in.array());
        data=data.trim();
        System.out.println("0");
        System.out.println(data);
        Thread ConsoleReader = new Thread(new ConsoleReader(data, channel, adr, all));
        ConsoleReader.start();

    }

//    private void doCommand(String command) throws IOException
//    {
//        switch (command)
//        {
//            case "info":
//                {
//                    System.out.println("1");
//                    String send = "";
//
//                    for (Integer key : all.keySet()) {
//                        send = send + ((all.get(key)).toString());
//
//                    }
//
//
//
//                    break;
//            }
//        }
//    }


}
