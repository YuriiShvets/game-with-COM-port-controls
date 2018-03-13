package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 17.09.2015.
 * @author Shvets
 */
import java.awt.Color;

public class BlueBall extends Enemy{
    BlueBall() {
        super();
        setColor(Color.CYAN);
        setR(6);
        setSpeed(5);
    }

    public boolean remove(){
        if(Math.sqrt(Math.pow(getX() - GamePanel.player.getX(), 2) + Math.pow(getY() - GamePanel.player.getY(), 2)) <= (getR() + GamePanel.player.getR())) {
            GamePanel.player.setShield();
            return true;
        }
        return false;
    }
}
