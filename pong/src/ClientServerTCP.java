import pong.view.ConnectionFrames;
import pong.view.LoadMenu;

import threadobjects.Ball;

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