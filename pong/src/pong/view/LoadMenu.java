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
public class LoadMenu implements ImageObserver, KeyListener {

    private JFrame frameWindow;
    private JPanel viewPanel;
    private JLabel view;
    private BufferedImage surface;
    private Boolean selectorPosition = true;
    //
    private String menuBackgroundPath = "src\\images\\start_menu.png";
    private File menuBackgroundFile = new File(menuBackgroundPath);
    //
    private String selectorPath = "src\\images\\selector.png";
    private File selectorFile = new File(selectorPath);
    private Image selectorImage;

    public LoadMenu(JFrame frameWindow, int id){
        this.frameWindow = frameWindow;
        this.frameWindow.setTitle("Menu");
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
    }

    public void keyReleased(KeyEvent e){
        try {
            if(e.isActionKey()) {
                if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                    System.out.println("false");
                    selectorPosition = false;
                    loadSelector();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("true");
                    selectorPosition = true;
                    loadSelector();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                System.out.println("Pressed Enter");
                if(selectorPosition){
                    frameWindow.removeKeyListener(this);
                    LoadGame loadGame = new LoadGame(frameWindow,0);
                    loadGame.background();
                    loadGame.ball();
                    loadGame.racketLeft(0);
                    loadGame.racketRight(0);
                }else{
                    System.out.println("Selector is Down");
                }
            }
        } catch (Exception en) {
            System.err.println("Error caused by:" + en);
        }
    }

    public void keyTyped(KeyEvent e){
    }

    public void loadStartMenu() throws Exception {
        try {
            surface = ImageIO.read(menuBackgroundFile);
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
            selectorImage = ImageIO.read(selectorFile);
            g.setColor(Color.blue);
            if(selectorPosition) {
                g.drawImage(selectorImage, 0, -87, this);
            }else{
                g.drawImage(selectorImage, 0, -3, this);
            }
            g.dispose();
            view.repaint();
        } catch (HeadlessException hle) {
            System.err.println("Error caused by: " + hle);
        }
    }
}