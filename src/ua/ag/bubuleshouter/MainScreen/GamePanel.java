package ua.ag.bubuleshouter.MainScreen;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Юрій on 29.08.2015.
 */
public class GamePanel extends JPanel implements Runnable {

    public static int WIDTH = 1350, HEIGHT = 630, panelHeight = 60;
    private Thread thread;                                                                          //створили змінну потоку
    private BufferedImage image;                                                                    //змінна полотна
    private Graphics2D g;                                                                           //змінна кісточки
    public static GameBack background;                                                              //задня панель
    private Panel panel;                                                                            //панель
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemys;
    public static ArrayList<Explosion> explosions;
    public static ArrayList<BlueBall> blueBalls;
    public static ArrayList<Enemy2> enemys2;
    private double timeToEnemy, timeToBlueBall, FPS, time, timeToEnemy2;                            //остання для регуляції часу гри
    public static byte weaponsMode;
    private static boolean PauseOn;
    private MENU menu;
    private STRING str;                                                                             //наш власний рядок
    private static int numKillEnemis;                                                               //кількість убитих ворогів
    private int pointsForVictory;                                                                   //очок для перемоги

    public GamePanel() {
        super();                                                                                    //викликаємо конструктор суперкласу
        setPreferredSize(new Dimension(WIDTH, HEIGHT + panelHeight));                               //встановлюємо розміри панелі
        setFocusable(true);                                                                         //"фокусуванн панелі"
        requestFocus();                                                                             //робимо вікно активним
        addKeyListener(new Listeners());                                                            //додаємо слухачa
        FPS = 35;
        weaponsMode = 1;
        PauseOn = false;
        str = new STRING();
        numKillEnemis = 0;
        pointsForVictory = 600;
    }

    public void start() {
        thread = new Thread(this, "GamePanelThread");                                               //визначили змінну потоку
        thread.start();                                                                             //запускаємо потік
    }

    public void run() {                                                                             //точка входу потоку
        image = new BufferedImage(WIDTH, HEIGHT + panelHeight, BufferedImage.TYPE_INT_RGB);         //визначаємо полотно
        g = (Graphics2D) image.getGraphics();                                                       //прив'язуємо полотно до кісточки
        background = new GameBack(Color.GREEN);                                                     //Створення заднього фону
        panel = new Panel(Color.WHITE, pointsForVictory);                                                             //створення панелі
        player = new Player(WIDTH / 2, HEIGHT / 2, 8, Color.yellow, false, false, false, false, 7); //створюємо гравця (x, y, r, радіус, 4 напрямки руху, швидкість
        bullets = new ArrayList<Bullet>();                                                          //ініціалізуємо кулі
        enemys = new ArrayList<Enemy>();
        explosions = new ArrayList<Explosion>();
        blueBalls = new ArrayList<BlueBall>();
        enemys2 = new ArrayList<Enemy2>();
        //shields = new ArrayList<Shield>();
        //bullet = new Bullet();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//зглажування графіки
        timeToEnemy = timeToBlueBall = timeToEnemy2 = System.nanoTime();
        menu = new MENU();

        while (true) {
            time = System.nanoTime();
            //System.out.println(PauseOn);
            if (!PauseOn) {
                gameUpdate();//обновлення об'єктів
                gameRender();//оновлення графіки гри
            } else {
                MENURender();
                System.out.println("Виводиться меню");
            }
            gameDraw();//вивод на екран
            try {
                if ((System.nanoTime() - time) / 1000000 < FPS)
                    thread.sleep((long) (FPS - ((System.nanoTime() - time) / 1000000)));    //швидкість гри
            } catch (InterruptedException e) {
                System.out.println("Потік був перерваний");
                e.printStackTrace();
            }
        }
    }

    private void gameUpdate() {                                                             //в цьому методі будуть обновлятися данні всіх наших об'єктів
        background.update();                                                                //обновляємо данні про фон

        player.update();                                                                    //обновляємо данні про гравця
        if (player.isDead()) {
            g.setStroke(new BasicStroke(10));                                               //задаємо товщину ліній
            str.draw("Game over", 350, 200, 100, 100, Color.RED, 20, g);                    //малюємо напис
            gameDraw();                                                                     //обновлюємо екран
            thread.stop();                                                                  //зупиняємо гру
        }

        if(isVictory()){
            g.setStroke(new BasicStroke(10));                                               //задаємо товщину ліній
            str.draw("You won", 400, 200, 100, 100, Color.RED, 20, g);                      //малюємо напис
            gameDraw();                                                                     //обновлюємо екран
            thread.stop();
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if (bullets.get(i).remove()) {                                                  //
                bullets.remove(i);                                                          //видалємо зайві кулі
                i--;                                                                        //
            }                                                                               //
        }
        if (weaponsMode == 1 && bullets.size() > 200)                                        //довжина хвоста
            bullets.remove(0);    //for limit of veaponsMode1

        if ((System.nanoTime() - timeToEnemy) / 1000000000 > 0.5) {                         //час до появи наступного ворога
            timeToEnemy = System.nanoTime();
            enemys.add(new Enemy());
        }
        for (int i = 0; i < enemys.size(); i++) {
            enemys.get(i).update();
            if (enemys.get(i).remove()) {
                enemys.remove(i);
                numKillEnemis++;                                                            //збільшуємо кількість убитих ворогів
                i--;
            }
        }

        if ((System.nanoTime() - timeToEnemy2) / 1000000000 > 10) {                         //новий ворог
            timeToEnemy2 = System.nanoTime();
            //enemys2.add (new Enemy2());
        }
        for (int i = 0; i < enemys2.size(); i++) {
            enemys2.get(i).update();
            if (enemys2.get(i).remove()) {
                enemys2.remove(i);
                i--;
            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if (explosions.get(i).remove()) {
                explosions.remove(i);
                i--;
            }
        }

        if ((System.nanoTime() - timeToBlueBall) / 1000000000 > 20) {
            timeToBlueBall = System.nanoTime();
            blueBalls.add(new BlueBall());
        }
        for (int i = 0; i < blueBalls.size(); i++) {
            blueBalls.get(i).update();
            if (blueBalls.get(i).remove()) {
                blueBalls.remove(i);
                i--;
            }
        }

        GamePanel.player.checkShield();

        collision();
    }

    private void gameRender() {                 //обновлення графіки
        background.draw(g);                     //малюємо фон

        for (Bullet bullet : bullets)
            bullet.draw(g);                     //малюємо кулю

        player.draw(g);                         //малюємо гравця

        for (Enemy enemy : enemys)
            enemy.draw(g);                      //малюємо ворога

        for (Explosion explosion : explosions)
            explosion.draw(g);                  //малюємо вибух

        for (BlueBall blueBall : blueBalls)
            blueBall.draw(g);                   //малюємо синій шарік
        /*if(shields.size() > 0);
            shields.get(0).draw(g);*/

        panel.draw(g);                          //малюємо панель
        //if(bullet.getIs_life())
        //

        for (Enemy2 enemy2 : enemys2)           //малюємо нового ворога
            enemy2.draw(g);
    }

    private void gameDraw() {                   //приєднуємо кісточку до екрану
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);        //виводимо все на екран
        g.dispose();                           //даемо команду зборщику мусора прибрати змінну g2
    }

    private void MENURender() {
        menu.draw(g);
    }

    private void collision() {
        for (int i = 0; i < enemys.size(); i++)
            if (i < enemys.size() - 1)
                for (int j = i + 1; j < enemys.size(); j++)
                    if (Math.sqrt(Math.pow(enemys.get(i).getX() - enemys.get(j).getX(), 2) + Math.pow(enemys.get(i).getY() - enemys.get(j).getY(), 2)) <= enemys.get(i).getR() * 2) {
                        enemys.get(i).newDirection(enemys.get(i).getY() < enemys.get(j).getY(), enemys.get(i).getY() > enemys.get(j).getY(), enemys.get(i).getX() < enemys.get(j).getX(), enemys.get(i).getX() > enemys.get(j).getX());
                        enemys.get(j).newDirection(enemys.get(j).getY() < enemys.get(i).getY(), enemys.get(j).getY() > enemys.get(i).getY(), enemys.get(j).getX() < enemys.get(i).getX(), enemys.get(j).getX() > enemys.get(i).getX());
                    }
        for (int i = 0; i < blueBalls.size(); i++)
            if (i < blueBalls.size() - 1)
                for (int j = i + 1; j < blueBalls.size(); j++)
                    if (Math.sqrt(Math.pow(blueBalls.get(i).getX() - blueBalls.get(j).getX(), 2) + Math.pow(blueBalls.get(i).getY() - blueBalls.get(j).getY(), 2)) <= blueBalls.get(i).getR() * 2) {
                        blueBalls.get(i).newDirection(blueBalls.get(i).getY() < blueBalls.get(j).getY(), blueBalls.get(i).getY() > blueBalls.get(j).getY(), blueBalls.get(i).getX() < blueBalls.get(j).getX(), blueBalls.get(i).getX() > blueBalls.get(j).getX());
                        blueBalls.get(j).newDirection(blueBalls.get(j).getY() < blueBalls.get(i).getY(), blueBalls.get(j).getY() > blueBalls.get(i).getY(), blueBalls.get(j).getX() < blueBalls.get(i).getX(), blueBalls.get(j).getX() > blueBalls.get(i).getX());

                    }
        for (int i = 0; i < blueBalls.size(); i++)
            for (int j = 0; j < enemys.size(); j++)
                if (Math.sqrt(Math.pow(blueBalls.get(i).getX() - enemys.get(j).getX(), 2) + Math.pow(blueBalls.get(i).getY() - enemys.get(j).getY(), 2)) <= blueBalls.get(i).getR() + enemys.get(j).getR()) {
                    blueBalls.get(i).newDirection(blueBalls.get(i).getY() < enemys.get(j).getY(), blueBalls.get(i).getY() > enemys.get(j).getY(), blueBalls.get(i).getX() < enemys.get(j).getX(), blueBalls.get(i).getX() > enemys.get(j).getX());
                    enemys.get(j).newDirection(enemys.get(j).getY() < blueBalls.get(i).getY(), enemys.get(j).getY() > blueBalls.get(i).getY(), enemys.get(j).getX() < blueBalls.get(i).getX(), enemys.get(j).getX() > blueBalls.get(i).getX());
                }
    }

    public static void setPauseOn(boolean inPauseOn) {
        PauseOn = inPauseOn;
    }

    public static boolean getPauseOn() {
        return PauseOn;
    }

    public static int getNumKillEnemis(){
        return numKillEnemis;
    }

    private boolean isVictory(){
        if(numKillEnemis >= pointsForVictory)
            return true;
        return false;
    }
}
