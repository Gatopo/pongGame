package threadobjects;

import pong.view.LoadGame;

/**
 * Created by mario on 26/08/14.
 */
public class Ball extends Thread {
    static int ballX,ballY;
    LoadGame ballClient = new LoadGame();
    LoadGame ballServer = new LoadGame();
    int i = 0;

    public void ballPositions(int posX, int posY){
        ballX = posX;
        ballY = posY;
    }

    public void run(){
        try{
            ballClient.ball(i,i);
            ballServer.ball(i,i);
            System.out.println("Positions x,y:" + i++);
            Thread.sleep(500);
        }catch(Exception e){

        }
    }

}
