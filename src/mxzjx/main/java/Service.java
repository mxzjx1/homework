import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Service extends JFrame {
    public static int PORT=10005;
    private static DatagramSocket socket;
    private static ArrayList<Client> mList=new ArrayList<>();
    public Service() {
        try {
            socket=new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    public static void groups(Client client) {
        if(client==null)
            return;
        mList.add(client);
    }

    private void receiveMessage() {
        byte[] buf=new byte[1024];
        DatagramPacket datagramPacket=new DatagramPacket(buf, buf.length);
        while(true) {
            try {
                socket.receive(datagramPacket);
                String json=new String(datagramPacket.getData(),0,datagramPacket.getLength());
                for(Client client:mList) {
                    //发送数据给每一个客户端
                    client.displaymsg(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void run() {
//		while(true) {
        receiveMessage();
//		}
    }


}