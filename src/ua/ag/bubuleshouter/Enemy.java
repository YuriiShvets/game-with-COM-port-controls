package ua.ag.bubuleshouter;

/**
 * Created by Юрій on 15.09.2015.
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.Random;

public class Enemy {

    private double x, y;
    private int r, speed, dx, dy; //радіус, швидкість, коефіціенти переміщення (dx, dy)
    private Color color;
    private boolean up, down, left, right;
    private Random random;
    private long t;

    public Enemy(){
        random = new Random();
        //y = random.nextInt(GamePanel.HEIGHT - 20) + 10;
        //x = random.nextInt(GamePanel.WIDTH - 20) + 10;
        //int rand = random.nextInt(GamePanel.HEIGHT) + 1;
        //y = rand >= GamePanel.HEIGHT/2 ? -20 : GamePanel.HEIGHT + 20;
        switch(random.nextInt(4)){
            case 0:{
                y = -20;
                x = random.nextInt(GamePanel.WIDTH - 40) + 20;
                break;
            }
            case 1:{
                y = GamePanel.HEIGHT + 20;
                x = random.nextInt(GamePanel.WIDTH - 40) + 20;
                break;
            }
            case 2:{
                x = -20;
                y = random.nextInt(GamePanel.HEIGHT - 40) + 20;
                break;
            }
            case 3:{
                x = GamePanel.WIDTH + 20;
                y = random.nextInt(GamePanel.HEIGHT - 40) + 20;
                break;
            }
        }
        r = 10;
        speed = 8;
        dx = dy = 0;
        color = Color.RED;
        newDirection(true, true, true, true);
    }

    public Enemy(int inX, int inY){
        x = inX;
        y = inY;
        r = 10;
        speed = 8;
        dx = dy = 0;
        color = Color.RED;
    }

    public void newDirection(boolean up2, boolean down2, boolean left2, boolean right2){//в параметрах можливі напрямки руху
        up = down = left = right = false;
    if(up2 && down2 && left2 && right2)
            switch(random.nextInt(8)){
        case 0:{
            up = true;
            break;
        }
        case 1:{
            up = right = true;
            break;
        }
        case 2:{
            right = true;
            break;
        }
        case 3:{
            right = down = true;
            break;
        }
        case 4:{
            down = true;
            break;
        }
        case 5:{
            down = left = true;
            break;
        }
        case 6:{
            left = true;
            break;
        }
        case 7:{
            left = up = true;
            break;
        }
    }
    if(!up2 && down2 && left2 && right2)
            //down = true;
            switch(random.nextInt(3)){
        case 0:{
            down = right = true;
            break;
        }
        case 1:{
            down = true;
            break;
        }
        case 2:{
            down = left = true;
            break;
        }
    }
    if(up2 && !down2 && left2 && right2)
            switch(random.nextInt(3)){
        case 0:{
            up = left = true;
            break;
        }
        case 1:{
            up = true;
            break;
        }
        case 2:{
            up = right = true;
            break;
        }
    }
    if(up2 && down2 && !left2 && right2)
            switch(random.nextInt(3)){
        case 0:{
            up = right = true;
            break;
        }
        case 1:{
            right = true;
            break;
        }
        case 2:{
            right = down = true;
            break;
        }
    }
    if(up2 && down2 && left2 && !right2)
            switch(random.nextInt(3)){
        case 0:{
            down = left = true;
            break;
        }
        case 1:{
            left = true;
            break;
        }
        case 2:{
            left = up = true;
            break;
        }
    }
    if(!up2 && down2 && !left2 && right2)
    down = right = true;
    if(!up2 && down2 && left2 && !right2)
    down = left = true;
    if(up2 && !down2 && !left2 && right2)
    up = right = true;
    if(up2 && !down2 && left2 && !right2)
    up = left = true;
    if(up2 && !down2 && !left2 && !right2)
    up = true;
    if(!up2 && down2 && !left2 && !right2)
    down = true;
    if(!up2 && !down2 && left2 && !right2)
    left = true;
    if(!up2 && !down2 && !left2 && right2)
    right = true;
    t = System.nanoTime();

}

    public void update(){
        if(((System.nanoTime() - t)/1000000000 > random.nextInt(3)+1) || (y <= r) || (y >= GamePanel.HEIGHT - r) || (x <= r) || (x >= GamePanel.WIDTH - r))//час до зміни напрямку руху
            newDirection(y > r, y < GamePanel.HEIGHT - r, x > r, x < GamePanel.WIDTH - r);
        if(up)
            dy = -speed;
        if(down)
            dy = speed;
        if(left)
            dx = -speed;
        if(right)
            dx = speed;
        if((up && left) || (up && right) || (down && left) || (down && right)){
            dx *= Math.sin(Math.toRadians (45));
            dy *= Math.cos(Math.toRadians (45));
        }
        //if((y < r) || (y > GamePanel.HEIGHT - r) || (x < r) || (x > GamePanel.WIDTH)) {
        //    newDirection();
        //}
        x += dx;
        y += dy;
        dx = dy = 0;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) (x - r), (int) (y - r), r * 2, r * 2);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) (x - r), (int) (y - r), r * 2, r * 2);
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

    public boolean remove(){
        for(Bullet bullet: GamePanel.bullets)
            if(Math.sqrt(Math.pow(x - bullet.getX(), 2) + Math.pow(y - bullet.getY(), 2)) <= r + bullet.getR()) {
                GamePanel.bullets.remove(bullet);
                GamePanel.explosions.add(new Explosion(x, y, r));
                return true;
            }
        if(GamePanel.player.getShieldStatus())
            if(Math.sqrt(Math.pow(x - GamePanel.player.getX(), 2) + Math.pow(y - GamePanel.player.getY(), 2)) <= (r + GamePanel.player.getShieldR())){
                GamePanel.explosions.add(new Explosion(x, y, r));
                GamePanel.player.decTimeToRemoveShield();
                return true;
            }
        return false;
    }

    public void setColor(Color inColor){
        color = inColor;
    }

    public void setR(int inR){
        r = inR;
    }

    public void setSpeed(int inSpeed){
        speed = inSpeed;
    }

    void setX(double inX){
        x = inX;
    }

    void setY(double inY){
        y = inY;
    }

    void setUp(boolean inUp){
        up = inUp;
    }

    void setDown(boolean inDown){
        down = inDown;
    }

    void setLeft(boolean inLeft){
        left = inLeft;
    }

    void setRight(boolean inRight){
        right = inRight;
    }
}
