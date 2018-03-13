package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 04.10.2015.
 * @author Shvets
 */
import java.awt.Color;

public class Enemy2 extends Enemy {

    public Enemy2(){
        super();
        setColor(Color.orange);
    }

    public void newDirection(boolean up2, boolean down2, boolean left2, boolean right2){
        setUp(up2);
        setDown(down2);
        setLeft(left2);
        setRight(right2);
        if(getX() == GamePanel.player.getX()){
            setLeft(false);
            setRight(false);
            if(getY() > GamePanel.player.getY())
                setDown(false);
            if(getY() < GamePanel.player.getY())
                setUp(false);
        }
        else
            if(getY() == GamePanel.player.getY()){
                setUp(false);
                setDown(false);
                if(getX() > GamePanel.player.getX())
                    setRight(false);
                if(getX() < GamePanel.player.getX())
                    setLeft(false);
            }
            else
                super.newDirection(up2 && getY() > GamePanel.player.getY(), down2 && getY() < GamePanel.player.getY(), left2 && getX() > GamePanel.player.getX(), right2 && getX() < GamePanel.player.getX());
        /*setUp(up2);
        setDown(down2);
        setLeft(left2);
        setRight(right2);
        if(getX() == GamePanel.player.getX()){
            setLeft(false);
            setRight(false);
            if(getX() > GamePanel.player.getX())
                setDown(false);
            if(getX() < GamePanel.player.getX())
                setUp(false);
            //setUp(getX() > GamePanel.player.getX());
            //setDown(getX() < GamePanel.player.getX());
        }*/
    }

    /*public void update(){

    }*/
}
