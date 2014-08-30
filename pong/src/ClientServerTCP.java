import pong.view.ConnectionFrames;
import pong.view.LoadMenu;
import threadobjects.Ball;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mario on 10/08/14.
 * Modified by Steven on 10/08/14.
 */
public class ClientServerTCP {

    public static void main (String args[]) throws Exception{
        ConnectionFrames playerWindow = new ConnectionFrames();
        playerWindow.run();
        Ball ball = new Ball();
        while(playerWindow.connectionType == null);
        System.out.println(playerWindow.connectionType);
        ball.run();
        while(playerWindow.connectionType.equals("server")){
            System.out.println("APP: Im now a Server");
        }
        while(playerWindow.connectionType.equals("client")){
            System.out.println("APP: Im now a Client");
        }
    }
}