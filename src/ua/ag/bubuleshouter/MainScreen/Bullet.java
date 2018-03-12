package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 13.09.2015.
 */
//import java.awt.Color;
import java.awt.*;
public class Bullet {

    private double x, y;//кординати
    private int r, speed;
    private Color color1;
    private boolean up, down, left, right;
    private int dx, dy;//коефіціенти зсуву

    public Bullet(){
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;
        color1 = Color.BLUE;
        speed = 15;
        up = GamePanel.player.getUp2();
        down = GamePanel.player.getDown2();
        left = GamePanel.player.getLeft2();
        right = GamePanel.player.getRight2();
        dx = dy = 0;
    }

    public void update() {
        if (GamePanel.weaponsMode == 0) {
            if (up)
                dy = -speed;
            if (down)
                dy = speed;
            if (left)
                dx = -speed;
            if (right)
                dx = speed;
            if ((up && left) || (up && down) || (down && left) && (down && right)) {
                dy *= Math.sin(Math.toRadians(45));
                dx *= Math.cos(Math.toRadians(45));
            }
            y += dy;
            x += dx;
            dx = dy = 0;
        }
    }

    public void draw (Graphics2D g){
        g.setColor (color1);
        g.fillOval ((int) (x - r), (int) (y - r), r*2, r*2);
    }

    public boolean remove(){
        if((y < -2 * r) || (y > GamePanel.HEIGHT) || (x < -2 * r) || (x > GamePanel.WIDTH))
            return true;
        /*for(Enemy enemy: GamePanel.enemys)
            if(Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2)) <= r + enemy.getR())
                return true;*/
        return false;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getR(){
        return r;
    }
    /*private boolean is_life;
    Bullet(){
        super(Player.x, Player.y, Player.getR()/3, Color.BLUE, Player.up2, Player.down2, Player.left2, Player.right2, Player.getSpeed()*2);
        is_life = false;
    }

    public boolean getIs_life(){
        return is_life;
    }

    public void setIs_life(boolean in_is_life){
        is_life = in_is_life;
    }

    public void update(){
        super.update();
        if(super.shot && !is_life)
            is_life = true;
    }*/
}
