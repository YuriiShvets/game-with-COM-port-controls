package ua.ag.bubuleshouter;

import jssc.SerialPort;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Shvets on 10.04.2017.
 */
public class COMPortWorker extends JFrame {

    private JTextField COMPortNumber;

    public COMPortWorker() {     //���� ���� ������ � main
        super("COM port");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(140, 87);

        Box verticalBox = Box.createVerticalBox();
        add(verticalBox);

        Box horizontalBox1 = Box.createHorizontalBox();

        horizontalBox1.add(new Label("COM"));
        horizontalBox1.add(COMPortNumber = new JTextField("3"));

        verticalBox.add(horizontalBox1);

        JButton openButton;
        verticalBox.add(openButton = new JButton("open"));

        setVisible(true);

        Action action = new AbstractAction() {          //анонімний клас для ідентифікації натиснення клавіші "Enter"
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openPort();
            }
        };

        COMPortNumber.addActionListener( action );

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPort();
            }

        });


    }

    void openPort() {
        SerialPort serialPort = new SerialPort("COM" + COMPortNumber.getText());
        //serialPort = new SerialPort(args[0]); //��'� ����� ������ �� ��������� ���������� �����
        try {
            //��������� ����
            serialPort.openPort();
            //���������� ���������
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //�������� ���������� ���������� �������
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            GameStart.portWasOpen(serialPort);
            setVisible(false);
            serialPort.addEventListener(new PortReader(serialPort));
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
}
