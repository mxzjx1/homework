import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.*;


public class Client extends JFrame   {

    private JTextArea jTextArea;
    private DatagramSocket socket;
    private String msg;
    private InetAddress ip;
    public String username;

    public Client(String name) {
        //窗口标题
        super(name);
        visualization();
        link();
        username=name;

    }

    public void visualization(){
        //尺寸大小
        setSize(400, 320);

        setLocation(100, 100);
        //固定大小
        setResizable(false);


        //设置容器
        JPanel jPanel = new JPanel();

        //布局
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        Container container = getContentPane();
        container.add(jPanel, BorderLayout.NORTH);


        //加入文本框
        JPanel panels = new JPanel();
        panels.add(new JLabel("聊天内容"));
        jPanel.add(panels);
        jTextArea = new JTextArea(12, 30);
        jTextArea.setEnabled(false);
        jTextArea.setLineWrap(true);
        jTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
        panels.add(jTextArea);


        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        jScrollPane.setSize(336, 113);

        jPanel.add(jScrollPane);


        //添加新容器并布局
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(new JLabel("消息"));
        JTextField jTextField = new JTextField(25);
        jPanel1.add(jTextField);
        JButton jButton = new JButton("发送");
        jPanel1.add(jButton);
        jButton.addActionListener(e -> {
            msg = jTextField.getText();
            jTextField.setText("");
            send(msg,username);


        });


       /* setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

        setVisible(true);

    }

    public void send(String msg,String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        jsonObject.put("username", username);
        String json = jsonObject.toJSONString();
        byte[] bytes = json.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, ip, Service.PORT);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void link() {
        Service.groups(this);
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName("127.0.0.1");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }}


    public void displaymsg(String json) {
        JSONObject jsonObject1 = JSON.parseObject(json);
        String  msg = jsonObject1.getString("msg");
        String  userneame = jsonObject1.getString("username");



        jTextArea.append(userneame+":"+msg+"\n");
        jTextArea.setCaretPosition(jTextArea.getText().length());
        
        


    }



}