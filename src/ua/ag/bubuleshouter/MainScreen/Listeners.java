package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 13.09.2015.
 * @author Shvets
 */

import java.awt.event.*;

public class Listeners implements KeyListener {

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();//отримуємо код натиснутої кнопки
        //if(key == KeyEvent.VK_UP ||key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
        //    Player.up2 = Player.down2 = Player.left2 = Player.right2 = false;
//        if (key == KeyEvent.VK_UP)
//            GamePanel.player.setUp(true);
//        if (key == KeyEvent.VK_DOWN)
//            GamePanel.player.setDown(true);
//        if (key == KeyEvent.VK_LEFT)
//            GamePanel.player.setLeft(true);
//        if (key == KeyEvent.VK_RIGHT)
//            GamePanel.player.setRight(true);
//        if (key == KeyEvent.VK_SPACE)
//            GamePanel.player.setIsFiring(true);
        /*if(key == KeyEvent.VK_ESCAPE && GamePanel.getPauseOn())
            GamePanel.setPauseOn(true);*/
        if (key == KeyEvent.VK_ESCAPE && !GamePanel.getPauseOn())
            GamePanel.setPauseOn(true);
        else if (key == KeyEvent.VK_ESCAPE && GamePanel.getPauseOn())
            GamePanel.setPauseOn(false);
        // if(key == KeyEvent.VK_SPACE)
        //    Player.shot = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();//отримуємо код відпущеної кнопки
//        if (key == KeyEvent.VK_UP)
//            GamePanel.player.setUp(false);
//        if (key == KeyEvent.VK_DOWN)
//            GamePanel.player.setDown(false);
//        if (key == KeyEvent.VK_LEFT)
//            GamePanel.player.setLeft(false);
//        if (key == KeyEvent.VK_RIGHT)
//            GamePanel.player.setRight(false);
//        if (key == KeyEvent.VK_SPACE)
//            GamePanel.player.setIsFiring(false);
        // if(key == KeyEvent.VK_SPACE)
        //    Player.shot = false;
    }
}
