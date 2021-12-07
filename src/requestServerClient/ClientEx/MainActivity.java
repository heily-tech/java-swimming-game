package ClientEx;


import ClientEx.activities.*;
import ClientEx.strokes.*;
import ClientEx.network.tcpClient;
//import ClientEx.network.udpClient;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    initActivity initActivity;
    SelectStrokeActivity selectStrokeActivity;
    SelectModeActivity selectModeActivity;
    PlayActivity playActivity;
    public freestyle freestyle;
    backStroke backStroke;
    butterfly butterfly;
    breastStroke breastStroke;
    static tcpClient client;
    //static udpClient udpClient;
    int gameState = 0;

    Player my = null, other = null;
    
    public void setGameState(int num)
    {
    	this.gameState = num;
    }
    
    public int getGameState() {
    	return this.gameState;
    }
    
    public void setMe(Player user) {
    	this.my = user;
    }
    
    public Player getMe() {
    	return this.my;
    }
    
    public void setOther(Player user) {
    	this.other = user;
    }
    
    public Player getOther() {
    	return this.other;
    }
    
//    public udpClient getUdpClient() {
//    	return this.udpClient;
//    }

    public tcpClient getClient()
    {
    	return client;
    }
    
    public static void main(String[] args) {
        MainActivity main = new MainActivity();
        

        main.setTitle("My Swimming Game");
        main.setSize(GAME_WIDTH, GAME_HEIGHT);
        main.setResizable(false);
        main.setLocationRelativeTo(null); //화면 중앙에 창 위치
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main.client = new tcpClient(main);
        //main.udpClient = new udpClient(main);
        
        main.initActivity = new initActivity(main, client);
        main.selectStrokeActivity = new SelectStrokeActivity(main);
        main.selectModeActivity = new SelectModeActivity(main);
        main.playActivity = new PlayActivity(main);
        
        main.backStroke = new backStroke(main);
        main.butterfly = new butterfly(main);
        main.breastStroke = new breastStroke(main);

        main.add(main.initActivity);

        main.setVisible(true);
    }
    public void sfx(String fileName) {
        try {
            String path = MainActivity.class.getResource("").getPath();
            System.out.println(path + " " + fileName);
            File file = new File(path + fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void change(String panelName) {
        if (panelName.equals("SelectStrokeActivity")) {
            getContentPane().removeAll();
            getContentPane().add(selectStrokeActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("SelectModeActivity")) {
            getContentPane().removeAll();
            getContentPane().add(selectModeActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("PlayActivity")) {
            getContentPane().removeAll();
            getContentPane().add(playActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("freestyle")) {
            getContentPane().removeAll();
            getContentPane().add(freestyle);
            revalidate();
            repaint();
        } else if (panelName.equals("backStroke")) {
            getContentPane().removeAll();
            getContentPane().add(backStroke);
            revalidate();
            repaint();
        } else if (panelName.equals("butterfly")) {
            getContentPane().removeAll();
            getContentPane().add(butterfly);
            revalidate();
            repaint();
        } else if (panelName.equals("breastStroke")) {
            getContentPane().removeAll();
            getContentPane().add(breastStroke);
            revalidate();
            repaint();
        }
    }
}
