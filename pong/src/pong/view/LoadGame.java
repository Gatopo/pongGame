package pong.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Steven on 17/08/2014.
 */
public class LoadGame implements ImageObserver, KeyListener{

    private JFrame frameWindow;
    private JPanel viewPanel;
    private JLabel view;
    private BufferedImage surface;
    private Boolean selectorPosition = true;
    private int YPosRight, YPosLeft;
    private int gamerID;
    private final String pathBackground = "src/images/background.png";
    private File fileBackground;
    //
    private String ballImagePath = "src/images/ball.png";
    private File ballFile = new File(ballImagePath);
    private Image ballImage;
    //
    String leftRacketPath = "src/images/racket_l.png";
    File leftRacketFile = new File(leftRacketPath);
    Image leftRacketImage;
    //
    String rightRacketPath = "src/images/racket_r.png";
    File rightRacketFile = new File(rightRacketPath);
    Image rightRacketImage;

    public LoadGame(JFrame frameWindow, int id ){
        this.frameWindow = frameWindow;
        this.frameWindow.setTitle("Pong");
        frameWindow.addKeyListener(this);
        view = new JLabel();
        surface = null;
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.setContentPane(view);
        YPosRight = 0;
        YPosLeft = 0;
        gamerID = id;
        fileBackground = new File(pathBackground);
    }

    public LoadGame(){
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
        return true;
    }

    public void keyPressed(KeyEvent e){
        System.out.println("gamerID: " + gamerID);
        if(e.isActionKey()) {
            if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                if(YPosLeft < 185 && gamerID == 0) {
                    YPosLeft += 5;
                }
                if( YPosRight < 185 && gamerID == 1) {
                    YPosRight += 5;
                }
                System.out.println("Y Position: " + YPosLeft);
                racketLeft(YPosLeft);
                racketRight(YPosRight);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println(YPosLeft>-155);
                if(YPosLeft > -155 && gamerID == 0) {
                    YPosLeft -= 5;
                }
                if(YPosRight > -155 && gamerID == 1) {
                    YPosRight -= 5;
                }
                System.out.println("Y Position: " + YPosLeft);
                racketLeft(YPosLeft);
                racketRight(YPosRight);
            }
        }
    }

    public void keyReleased(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){
    }

    public void background() {
        try{
            surface = ImageIO.read(fileBackground);
            view = new JLabel(new ImageIcon(surface));
            frameWindow.setContentPane(view);
            view.repaint();
            //size and show the frame
            frameWindow.pack();
            frameWindow.setVisible(true);
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        } catch (IOException ioe){
            System.out.println("Error caused by: " + ioe);
        }
    }

    public void ball(int posX, int posY){
        try {
            Graphics g = surface.getGraphics();
            ballImage = ImageIO.read(ballFile);
            g.setColor(Color.blue);
            g.drawImage(ballImage, posX, posY, this);
            g.dispose();
            view.repaint();
            //Despues de que ambos usuarios presionaron ENTER
            //GameObjects.setBallPositions(posX, posY);
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        } catch (IOException ioe){
            System.out.println("Error caused by: " + ioe);
        }
    }

    public void racketLeft(int YPos){
        try {
            Graphics g = surface.getGraphics();
            leftRacketImage = ImageIO.read(leftRacketFile);
            g.setColor(Color.blue);
            g.drawImage(leftRacketImage, -402, YPos, this);
            g.dispose();
            view.repaint();
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        } catch (IOException ioe){
            System.out.println("Error caused by: " + ioe);
        }
    }

    public void racketRight(int YPos){
        try {
            background();
            ball(0,0);
            racketLeft(YPosLeft);
            Graphics g = surface.getGraphics();
            rightRacketImage = ImageIO.read(rightRacketFile);
            g.setColor(Color.blue);
            g.drawImage(rightRacketImage, 400, YPos, this);
            g.dispose();
            view.repaint();
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        } catch (IOException ioe){
            System.out.println("Error caused by: " + ioe);
        }
    }
}
