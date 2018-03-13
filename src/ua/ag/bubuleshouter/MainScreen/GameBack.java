package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 30.08.2015.
 * @author Shvets
 */
import java.awt.*;

public class GameBack {

    private Color color;

    public GameBack(Color inColor){
        color = inColor;
    }

    public void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.setColor(color.darker());//робимо колір темнішим
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);//задаємо квадрат
    }

    public Color getColor(){
        return color;
    }
}
