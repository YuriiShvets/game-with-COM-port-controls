package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 16.09.2015.
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class Explosion {
    private double x, y;
    private int r, speed, maxR;
    private Color color;

    public Explosion(double inX, double inY, int inR){
        x = inX;
        y = inY;
        r = inR;
        color = Color.ORANGE;
        speed = 3;
        maxR = 150;
    }

    public void update(){
        r += speed;
    }

    public void draw(Graphics2D g){
        g.setColor (color);
        g.drawOval((int) (x - r), (int) (y - r), r * 2, r * 2);
    }

    public boolean remove(){
        if(r > maxR)
            return true;
        return false;
    }
}
