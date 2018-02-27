package ua.ag.bubuleshouter;

import javax.swing.JFrame;
/**
 * Created by Юрій on 29.08.2015.
 */

public class GameStart {

    public static void main (String args[]){
        /*Random random = new Random();
        for (int i = 0; i < 10; i++)
            System.out.println(random.nextInt(4));//виводить випалкове число в діапазоні [0; 3]*/
        GamePanel panel = new GamePanel();
        JFrame StartFrame = new JFrame("BubleShooter");//створюємо фрейм і даємо йому назву
        StartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//встановлюємо закриття програми по натисненню хрестика
        StartFrame.setContentPane(panel);//додаємо панельку
        StartFrame.pack();//робимо так щоб фрейм займав рівно стільки місця скільки займають його компоненти
        StartFrame.setLocationRelativeTo(null);//встановлюємо фрейм по центру екрана
        StartFrame.setVisible(true);//робимо фрейм видимим
        panel.start();//запускаємо потік
    }
}
