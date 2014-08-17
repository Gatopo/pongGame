import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.SocketHandler;

/**
 * Created by mario on 10/08/14.
 * Modified by Steven on 10/08/14.
 */
public class ClientServerTCP {
    //JTextfield values
    static String ipAddressFromJTextfield;
    static String portNumberFromJTextfield;

    public static void main (String args[]){
        ClientServerTCP.createConnectionWindow();
        ServerSocket serverSocket;
        Socket clientSocket;
        String clientIP, portNumber;
        //Aqui se reciben los parametros de IP y puerto, de la interfaz grafica

        clientIP = ipAddressFromJTextfield;
        portNumber = portNumberFromJTextfield;

        //Empezar a construir parametros para la conexion

        //}
        /*
        while(clientIP.isEmpty()){
            //recibir el parametro del puerto
            try {
                if(port != null) {
                    serverSocket = new ServerSocket(port);
                    while(true){
                        Socket socket = serverSocket.accept();
                        //tirar a pantalla de menu
                        System.out.println("Connection accepted");
                        // menu.start();
                    }
                }
            } catch (IOException ioe) {
                System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
            }
        }*/

    }

    private static void createConnectionWindow(){
        try {
            //Create the frame.
            final JFrame frameWindow = new JFrame("Pong");
            //Close the window when the frame is closed.
            frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Creates the panel.
            final JPanel viewPanel = new JPanel();
            //Sets the layout at the principal panel.
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("Pong Connection");
            JButton connectTo = new JButton("Connect to");
            JButton waitForAConnection = new JButton("Wait for a connection");
            connectTo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    createConnectToWindow(frameWindow, viewPanel);
                }
            });
            waitForAConnection.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    createWaitingForAConnectionWindow(frameWindow, viewPanel);
                    executionServer();
                }
            });
            //Adds the created components.
            viewPanel.add(title);
            viewPanel.add(connectTo);
            viewPanel.add(waitForAConnection);
            frameWindow.add(viewPanel);
            //Size the frame.
            frameWindow.pack();
            frameWindow.setSize(250,250);
            //Show it.
            frameWindow.setVisible(true);
        }catch(HeadlessException hle){
            System.err.println("Error caused by: " + hle);
        }
    }

    private static void createConnectToWindow(JFrame jframe, JPanel panel){
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //Create the title in the window.
        JLabel title = new JLabel("Insert the connection fields");
        //Create the JTextfield
        final JTextField ipAddressJTextfield = new JTextField();
        final JTextField portNumberJTextfield = new JTextField();
        JButton makeConnection = new JButton("Connect");
        makeConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("makeConnection was clicked");
                ipAddressFromJTextfield = ipAddressJTextfield.getText();
                portNumberFromJTextfield = portNumberJTextfield.getText();
                System.out.println("ip address: " + ipAddressFromJTextfield + "and the port number: " + portNumberFromJTextfield);
                executionClient();
            }
        });
        panel.add(title);
        panel.add(new JLabel("IP Adress: "));
        panel.add(ipAddressJTextfield);
        panel.add(new JLabel("Port(Opcional): "));
        panel.add(portNumberJTextfield);
        panel.add(makeConnection);
        jframe.add(panel);
        jframe.pack();
        jframe.setSize(250,250);
        //Show it.
        jframe.setVisible(true);
    }

    private static void createWaitingForAConnectionWindow(JFrame frameWindow, JPanel panel){
        try {
            panel.removeAll();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            //Create the title in the window.
            JLabel title = new JLabel("Waiting for a connection...");
            //Create the JTextfield
            panel.add(title);
            frameWindow.add(panel);
            frameWindow.pack();
            frameWindow.setSize(250, 250);
            //Show it.
            frameWindow.setVisible(true);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
            e.printStackTrace();
        }
        //executionServer();
    }

    private static void createStartClientServerWindow(JFrame jFrame, JPanel panel){
        panel.removeAll();
        //Add Images
    }

    private static void executionClient(){
        Socket clientSocket;
        String clientIP, portNumber;
        //Aqui se reciben los parametros de IP y puerto, de la interfaz grafica
        clientIP = ipAddressFromJTextfield;
        portNumber = portNumberFromJTextfield;
        Integer port = Integer.parseInt(portNumber);
        try {
            if(!clientIP.isEmpty()) {
                InetAddress IP = InetAddress.getByName(clientIP);
                clientSocket = new Socket(IP, port);
            }


        } catch (UnknownHostException ue) {
            System.err.println("ERROR: IP addres could not be determined. Exception caused by: " + ue);
        } catch (IOException ioe){
            System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
        }
    }

    private static void executionServer(){
        ServerSocket serverSocket;
        String clientIP, portNumber;
        //Aqui se reciben los parametros de IP y puerto, de la interfaz grafica
        clientIP = ipAddressFromJTextfield;
        portNumber = "4502";
        Integer port = Integer.parseInt(portNumber);
        //while(clientIP.isEmpty()){
        //recibir el parametro del puerto
        try {
            if(port != null) {
                serverSocket = new ServerSocket(port);
                //while(true){
                Socket socket = serverSocket.accept();
                //tirar a pantalla de menu
                System.out.println("Connection accepted");
                // menu.start();
                // }
            }
        } catch (IOException ioe) {
            System.err.println("ERROR: Socket connection failed. Exception caused by: " + ioe);
        }
        //}
    }
}