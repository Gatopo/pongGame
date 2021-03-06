package pong.view;

import data.Conections;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

/**
 * Created by mario on 26/08/14.
 */
public class ConnectionFrames extends Thread {

    static boolean startConnection= false;
    static boolean stateOfConnection = false;
    public static String connectionType;
    static BufferedReader clientBuffer;
    static BufferedReader serverBuffer;
    static Conections connectionHandler = new Conections();
    static String ipAddressFromJTextfield;
    static String portNumberFromJTextfield;

    private void createConnectionWindow(){
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
                    stateOfConnection = connectionHandler.serverCom("4502");
                    if(stateOfConnection) {
                        LoadMenu loadMenuServer = new LoadMenu(frameWindow, 0);
                        try {
                            loadMenuServer.loadStartMenu();
                            loadMenuServer.loadSelector();
                            connectionType = "server";
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

    private void createConnectToWindow(final JFrame jframe, JPanel panel){
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
                stateOfConnection = connectionHandler.clientCom(ipAddressFromJTextfield, portNumberFromJTextfield);
                //executionClient();
                if (stateOfConnection){
                    LoadMenu loadMenuClient = new LoadMenu(jframe, 1);
                    try {
                        loadMenuClient.loadStartMenu();
                        loadMenuClient.loadSelector();
                        connectionType = "client";
                    } catch (Exception e) {
                        System.err.println("Error caused by: " + e);
                    }
                }else{
                    System.out.println("State connection is false");
                }
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

    private void createWaitingForAConnectionWindow(JFrame frameWindow, JPanel panel){
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

    public void run(){
        createConnectionWindow();
    }
}
