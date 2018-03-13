package ua.ag.bubuleshouter.MainScreen;

/**
 * Created by Юрій on 17.10.2015.
 * @author Shvets
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class STRING {

    public STRING(){
    }

    public void draw(String string, int x, int y, int LWidth, int LHeight, Color color, int retread, Graphics2D g){ //передостаннє - це пробіл
        g.setColor(color);
        for(int i = 0; i < string.length(); i++){
            switch(string.charAt(i)){
                case 'M':
                    drawM(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'E':
                    drawE(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'N':
                    drawN(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'U':
                    drawU(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'G':
                    drawG(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'a':
                    draw_a(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'm':
                    draw_m(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'e':
                    draw_e(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'O':
                    drawO(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'v':
                    draw_v(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'r':
                    draw_r(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'o':
                    draw_o(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'Y':
                    drawY(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'u':
                    draw_u(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'w':
                    draw_w(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
                case 'n':
                    draw_n(x + LWidth * (i - 1), y, LWidth - retread, LHeight, g);
                    break;
            }
        }
    }

    private void drawM(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x, y + height);
        g.drawLine(x + width, y, x + width, y + height);
        g.drawLine(x, y, x + width/2, y + height/2);
        g.drawLine(x + width, y, x + width/2, y + height/2);
    }

    private void drawE(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y + height/2, x + width, y + height/2);
        g.drawLine(x, y + height, x + width, y + height);
    }

    private void drawN(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x + width, y, x + width, y + height);
    }

    private void drawU(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x + width, y, x + width, y + height);
    }

    private void drawG(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x + width, y + height, x + width, y + height / 2);
        g.drawLine(x + width, y + height / 2, x + width/2, y + height / 2);
    }

    private void draw_a(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y + height, x + width / 2, y + height / 2);
        g.drawLine(x + width / 2, y + height / 2, x+ width, y + height);
        g.drawLine(x + width / 3, y + height / 5 * 4, x + width / 3 *2, y + height / 5 * 4);
    }

    private void draw_m(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y + height / 2, x, y + height);
        g.drawLine(x, y + height / 2, x + width, y + height / 2);
        g.drawLine(x + width / 2, y + height / 2, x + width / 2, y + height);
        g.drawLine(x + width ,y + height / 2, x + width, y + height);
    }

    private void draw_e(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y + height / 2, x, y + height);
        g.drawLine(x, y + height / 2, x + width, y + height / 2);
        g.drawLine(x ,y + height / 4 * 3, x + width, y + height / 4 * 3);
        g.drawLine(x, y + height, x + width, y + height);
    }

    private void drawO(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y, x, y + height);
        g.drawLine(x + width, y, x + width, y + height);
        g.drawLine(x, y + height, x + width, y + height);
    }

    private void draw_v(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y + height / 2, x + width / 2, y + height);
        g.drawLine(x + width, y + height / 2, x + width / 2, y + height);
    }

    private void draw_r(int x, int y, int width, int height, Graphics2D g){
        y += height / 2;
        height /= 2;
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y + height / 2, x + width, y + height / 2);
        g.drawLine(x + width, y, x + width, y + height / 2);
        g.drawLine(x, y + height / 2, x + width, y + height);
    }

    private void draw_o(int x, int y, int width, int height, Graphics2D g){
        y += height / 2;
        height /= 2;
        g.drawLine(x, y, x + width, y);
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x + width, y, x+ width, y + height);
    }

    private void drawY(int x, int y, int width, int height, Graphics2D g){
        g.drawLine(x, y, x + width / 2, y + height / 2);
        g.drawLine(x + width / 2, y + height / 2, x + width, y);
        g.drawLine(x + width / 2, y + height / 2, x + width / 2, y + height);
    }

    private void draw_u(int x, int y, int width, int height, Graphics2D g){
        y += height / 2;
        height /= 2;
        g.drawLine(x, y, x, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x + width, y + height, x + width, y);
    }

    private void draw_w(int x, int y, int width, int height, Graphics2D g){
        y += height / 2;
        height /= 2;
        g.drawLine(x, y, x + width / 3, y + height);
        g.drawLine(x + width / 3, y + height, x + width / 2, y);
        g.drawLine(x + width / 2, y, x + width / 3 * 2, y + height);
        g.drawLine(x + width / 3 * 2, y + height, x + width, y);
    }

    private void draw_n(int x, int y, int width, int height, Graphics2D g){
        y += height / 2;
        height /= 2;
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x, y, x, y + height);
        g.drawLine(x + width, y, x + width, y + height);
    }
}
