package ua.ag.bubuleshouter;

/**
 * Created by Юрій on 18.09.2015.
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Shield {
    private Color color2;
    private int lineWidth;
    private double x, y;
    private int r;
    private double timeToRemove, time;

    public Shield(){
        super();
        lineWidth = 2;
        r = GamePanel.player.getR() + lineWidth;
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        timeToRemove = 10;
        time = System.nanoTime()/1000000000;
    }

    /*public Shield(double inX, double inY){
        lineWidth = 2;
        r = GamePanel.player.getR() + lineWidth;
        x = inX;
        y = inY;
    }*/

    public void update(){
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
    }

    public void draw(Graphics2D g){
        g.setStroke(new BasicStroke(lineWidth));
        g.setColor(Color.BLUE);
        g.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);
        g.setColor(Color.CYAN);
        g.drawOval((int) (x - r), (int) (y - r), r * 2, r * 2);
    }

    /*public void draw(Graphics2D g, double inX, double inY){
        g.setStroke(new BasicStroke(lineWidth));
        g.setColor(Color.BLUE);
        g.fillOval((int) (inX - r), (int) (inY - r), r * 2, r * 2);
        g.setColor(Color.CYAN);
        g.drawOval((int) (inX - r), (int) (inY - r), r * 2, r * 2);
    }*/

    public boolean remove(){
        /*for(Enemy enemy: GamePanel.enemys)
            if(Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2)) <= (r + enemy.getR())){
                GamePanel.enemys.remove(enemy);
                return true;
            }*/
        if(System.nanoTime()/1000000000 -time >= timeToRemove)
            return true;
        return false;
    }

    public int getR(){
        return r;
    }

    public void increesTimeToRemove(){
        time = System.nanoTime()/1000000000;
    }

    public void decTimeToRemove(){
        timeToRemove--;
    }

    public int getShieldTime(){
        return (int) (timeToRemove - (System.nanoTime()/1000000000 - time));
    }
}
