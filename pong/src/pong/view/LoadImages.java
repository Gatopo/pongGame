package pong.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;

/**
 * Created by Steven on 17/08/2014.
 */
public class LoadImages implements ImageObserver, KeyListener {

    JFrame frameWindow;
    JPanel viewPanel;
    JLabel view;
    BufferedImage surface;
    Boolean selectorPosition = true;

    public LoadImages(){
        frameWindow = new JFrame("Menu");
        frameWindow.addKeyListener(this);
        view = new JLabel();
        surface = null;
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.setContentPane(view);
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
        return true;
    }

    public void keyPressed(KeyEvent e){
        System.out.println("Key Pressed: " + e.getKeyChar());
    }

    public void keyReleased(KeyEvent e){
        if(e.isActionKey()){
            try {
                if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    System.out.println("false");
                    selectorPosition = false;
                    loadSelector();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("true");
                    selectorPosition = true;
                    loadSelector();
                }
            }catch(Exception en){
                System.err.println("Error caused by:" +en);
            }
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("Pressed Enter");
            if(selectorPosition){
                System.out.print("Selector is Up");
            }else{
                System.out.println("Selector is Down");
            }
        }
    }

    public void keyTyped(KeyEvent e){
        System.out.println("KeyCode:" + e.getKeyCode());
    }

    public void loadStartMenu() throws Exception {
        try {
            String path = "src\\images\\start_menu.png";
            File file = new File(path);
            surface = ImageIO.read(file);
            view = new JLabel(new ImageIcon(surface));
            frameWindow.setContentPane(view);
            view.repaint();
            //size and show the frame
            frameWindow.pack();
            frameWindow.setVisible(true);
            //frameWindow.setLocationByPlatform(true);
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        }
    }

    public void loadSelector() throws Exception {
        try {
            loadStartMenu();
            Graphics g = surface.getGraphics();
            String path = "src\\images\\selector.png";
            File file = new File(path);
            Image image = ImageIO.read(file);
            g.setColor(Color.blue);
            if(selectorPosition) {
                g.drawImage(image, 0, -87, this);
            }else{
                g.drawImage(image, 0, -3, this);
            }
            g.dispose();
            view.repaint();
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        }
    }
}
