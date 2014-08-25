import pong.view.LoadMenu;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mario on 10/08/14.
 * Modified by Steven on 10/08/14.
 */
public class ClientServerTCP {
    //JTextfield values
    static String ipAddressFromJTextfield;
    static String portNumberFromJTextfield;
    static Conections connectionHandler = new Conections();
    static boolean startConnection= false;
    static boolean stateOfConnection = false;
    static String connectionType;

    public static void main (String args[]) throws Exception{
        ClientServerTCP.createConnectionWindow();

        /*LoadImages loadMenu = new LoadImages();
        loadMenu.loadStartMenu();
        loadMenu.loadSelector();*/
        Socket serverSocket = null;
        InputStream isServer = null;
        OutputStream osServer = null;
        BufferedReader bfrServer = null;
        PrintWriter pwServer = null;
        Socket clientSocket = null;
        InputStream isClient = null;
        OutputStream osClient = null;
        BufferedReader bfrClient = null;
        PrintWriter pwClient = null;
        while(!stateOfConnection);
        if(connectionType.equals("server")){
            serverSocket = connectionHandler.serverTCPSocket;
            isServer = serverSocket.getInputStream();
            osServer = serverSocket.getOutputStream();
            bfrServer = new BufferedReader(new InputStreamReader(isServer));
            pwServer = new PrintWriter(osServer);
        }else{
            clientSocket = connectionHandler.clientTCPSocket;
            isClient = clientSocket.getInputStream();
            osClient = clientSocket.getOutputStream();
            bfrClient = new BufferedReader(new InputStreamReader(isClient));
            pwClient = new PrintWriter(osClient);
        }
        int tempCont = 0;
        while(connectionType.equals("server")){
            if(tempCont < 1000) {
                pwServer.write(tempCont + " \n");
                System.out.println("Data Send to the client: " + tempCont++);
            }
            bfrServer.readLine();
            System.out.println("Data Recived from the client " + tempCont++);
        }
        String data;
        while(connectionType.equals("client")){
            data = bfrClient.readLine();
            System.out.println("Data Recived from server: " + data);
            pwServer.write(1 + " \n");
            System.out.println("Data Send to the server: 1");
        }
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
            frameWindow.setResizable(false);

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
                    //while(startConnection);
                    connectionType = "server";
                    stateOfConnection = connectionHandler.serverCom("4502");
                    if(stateOfConnection) {
                        LoadMenu loadMenuServer = new LoadMenu(frameWindow, 0);
                        try {
                            loadMenuServer.loadStartMenu();
                            loadMenuServer.loadSelector();
                        } catch (Exception e) {
                            System.out.println("Error caused by: " + e);
                            e.printStackTrace();
                        }
                    }
                }
            });
        }catch(HeadlessException hle){
            System.err.println("Error caused by: " + hle);
        }catch(Exception e){
            System.err.println("Error caused by: " + e);
        }
    }

    private static void createConnectToWindow(final JFrame jframe, JPanel panel){
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
                ipAddressFromJTextfield = ipAddressJTextfield.getText();
                portNumberFromJTextfield = portNumberJTextfield.getText();
                System.out.println("ip address: " + ipAddressFromJTextfield + " and the port number: " + portNumberFromJTextfield);
                connectionType = "client";
                stateOfConnection = connectionHandler.clientCom(ipAddressFromJTextfield, portNumberFromJTextfield);
                //executionClient();
                if (stateOfConnection){
                    LoadMenu loadMenuClient = new LoadMenu(jframe, 1);
                    try {
                        loadMenuClient.loadStartMenu();
                        loadMenuClient.loadSelector();
                    } catch (Exception e) {
                        System.err.println("Error caused by: " + e);
                    }
                }else{
                    System.out.println("State connection is false");
                }
            }
        });
        panel.add(title);
        panel.add(new JLabel("IP Address: "));
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
            startConnection = true;
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
}