package ua.ag.bubuleshouter;

/**
 * Created by Юрій on 30.08.2015.
 */
import java.awt.*;

public class Player {

    private double x, y;//кординати гравця
    private int r; //радіус гравця
    private int speed;//щвидкість гравця
    private Color color1;
    private boolean up, down, left, right;//стан кнопок
    private boolean up2, down2, left2, right2;//напрямок "ствола" (за замовчуванням вгору)
    private boolean isFiring;
    private double dx, dy;//коефіціент зсуву.
    private Shield shield;
    private boolean shieldOn;

    public Player(double in_x, double in_y, int in_r, Color in_color1, boolean in_up, boolean in_down, boolean in_left, boolean in_right, int in_speed){
        x = in_x;
        y = in_y;
        r = in_r;
        color1 = in_color1;//визначали колір гравця
        up = in_up;
        down = in_down;
        left = in_left;
        right = in_right;
        speed = in_speed;
        up2 = true;
        down2 = left2 = right2 = isFiring = false;
        dx = dy = 0;
        shieldOn = false;
    }

    public void update(){//оновлення інформації про гравця
        if(up || down || left || right){
            up2 = up;
            down2 = down;
            left2 = left;
            right2 = right;
        }
        if (up && y > r)
            dy = -speed;
        if (down && y < GamePanel.HEIGHT - r)
            dy = speed;
        if (left && x > r)
            dx = -speed;
        if (right && x < GamePanel.WIDTH - r)
            dx = speed;
        if ((up && left) || (up && right) || (down && left) || (down && right)){
            dy *= Math.sin (Math.toRadians (45));
            dx *= Math.cos (Math.toRadians (45));
        }
        y += dy;
        x += dx;
        dx = dy = 0;//обнуляємо зсув (для того щоб зупинявся коли нічого не натискаємо)
        if (isFiring)
            GamePanel.bullets.add(new Bullet());
        if(GamePanel.weaponsMode == 1)
            GamePanel.bullets.add(new Bullet());
        if(shieldOn)
            shield.update();
    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillOval((int)x - r, (int) y - r, 2 * r, 2 * r);//заповнюємо овал
        g.setStroke (new BasicStroke(2));//збільшуємо товщину лінії
        g.setColor(color1.darker());
        g.drawOval((int) x - r, (int) y - r, r * 2, r * 2);
        if(shieldOn)
            shield.draw(g);
    }

    public boolean isDead(){
        if(!shieldOn)
            for(Enemy enemy: GamePanel.enemys)
             if(Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2)) < (r + enemy.getR() - 2))
                 return true;
        return false;
    }

    public void setUp (boolean inUp){
        up = inUp;
    }

    public void setDown (boolean inDown){
        down = inDown;
    }

    public void setLeft (boolean inLeft){
        left = inLeft;
    }

    public void setRight (boolean inRight){
        right = inRight;
    }

    public void setIsFiring (boolean inIsFiring){
        isFiring = inIsFiring;
    }

    public int getR(){
        return r;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public boolean getUp2 (){
        return up2;
    }

    public boolean getDown2 (){
        return down2;
    }

    public boolean getLeft2 (){
        return left2;
    }

    public boolean getRight2 (){
        return right2;
    }

    public void setShield(){
        if(!shieldOn) {
            shield = new Shield();
            shieldOn = true;
        }
        else
            shield.increesTimeToRemove();
    }

    public void deleteShield(){
        if(shieldOn){
            shieldOn = false;
            shield = null;
        }
    }

    public void checkShield(){
        if(shieldOn && shield.remove())
            deleteShield();
    }

    public int getShieldR(){
        return shield.getR();
    }

    public boolean getShieldStatus(){
        return shieldOn;
    }

    public int getShieldTime(){
        if(!shieldOn)
            return 0;
        return shield.getShieldTime();
    }

    public void decTimeToRemoveShield(){
        if(shieldOn)
            shield.decTimeToRemove();
    }
}
