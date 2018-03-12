package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 22.09.2015.
 */
import java.awt.*;

public class Panel extends GameBack {

    Enemy enemy;
    int pointToWin;

    public Panel(Color inColor, int pointToWin){
        super(inColor);
        this.pointToWin = pointToWin;
        enemy = new Enemy(200, GamePanel.HEIGHT + 30);
    }

    public void draw(Graphics2D g){
        g.setColor(getColor().darker());
        g.fillRect(0, GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.panelHeight);
        g.setColor(getColor().darker().darker().darker());
        g.setStroke(new BasicStroke(3));
        g.drawLine(0, GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.HEIGHT);
        //shield.draw(g);
        g.setColor(Color.BLUE);
        g.fillOval(5, GamePanel.HEIGHT + 21, 19, 19);
        g.setColor(Color.CYAN);
        g.drawOval(5, GamePanel.HEIGHT + 21, 19, 19);
        g.setColor(Color.BLUE);
        g.fillRect(30, GamePanel.HEIGHT + 23, GamePanel.player.getShieldTime() * 12, 15);   //заповнюємо резерв щита
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawRect(30, GamePanel.HEIGHT + 23, 120, 15);


        //-----------------Відмальовка шкали вбитих ворогів----------------------------
        Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 80);
        g.setFont(font);
        //g.drawString("ыо\nмядшвамилрываглм", 270, 220);
        enemy.draw(g);
        g.setColor(Color.RED);
        g.fillRect(220, GamePanel.HEIGHT + 23, GamePanel.getNumKillEnemis(), 15);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawString(((Integer)GamePanel.getNumKillEnemis()).toString(), 220 + pointToWin, GamePanel.HEIGHT + 60);
        g.drawRect(220, GamePanel.HEIGHT + 23, pointToWin, 15);                                     //передостаннє - довжина лінійки
    }
}
