package ua.ag.bubuleshouter;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ua.ag.bubuleshouter.MainScreen.GamePanel;

/**
 * Created by User on 12.03.2018.
 */
public class PortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    PortReader (SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0){
            try {
                //�������� ����� �� ����������, ������������ ������ � �.�.
                String data = serialPort.readString(event.getEventValue());
                System.out.println(data);
                //System.out.println('1'==(data.charAt(0)));
                for(int i = 0; i < data.length(); i++) {
                    if ('1' == data.charAt(i)) {
                        System.out.println("1 was resewed");
                        GamePanel.player.setUp(true);
                    }
                    if ('2' == data.charAt(i)) {
                        System.out.println("2 was resewed");
                        GamePanel.player.setUp(false);
                    }
                    if ('3' == data.charAt(i)) {
                        System.out.println("3 was resewed");
                        GamePanel.player.setRight(true);
                    }
                    if ('4' == data.charAt(i)) {
                        System.out.println("4 was resewed");
                        GamePanel.player.setRight(false);
                    }
                    if ('5' == data.charAt(i)) {
                        System.out.println("5 was resewed");
                        GamePanel.player.setDown(true);
                    }
                    if ('6' == data.charAt(i)) {
                        System.out.println("6 was resewed");
                        GamePanel.player.setDown(false);
                    }
                    if ('7' == data.charAt(i)) {
                        System.out.println("7 was resewed");
                        GamePanel.player.setLeft(true);
                    }
                    if ('8' == data.charAt(i)) {
                        System.out.println("8 was resewed");
                        GamePanel.player.setLeft(false);
                    }
                }


            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}
