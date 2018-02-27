package ua.ag.bubuleshouter;

/**
 * Created by Юрій on 13.10.2015.
 */
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class MENU {
    private int y;
    private Color color;
    private STRING str;

    public MENU(){
        color = Color.MAGENTA;
        str = new STRING();
        y = 15;
    }
    public void draw(Graphics2D g){
        g.setColor(color);
        //(g.fillRect(475, 45, 400, 600);
        g.fillRoundRect(475, y, 400, 600, 500, 100);
        g.setColor(color.darker().darker());
        g.setStroke(new BasicStroke(10));
        g.drawRoundRect(475, y, 400, 600, 500, 100);
        str.draw("MENU", 620, y + 25, 60, 100, Color.black, 20, g);
    }
}
