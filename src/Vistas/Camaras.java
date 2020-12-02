package Vistas;

import com.sun.jna.NativeLong;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Camaras extends JFrame {
    private JPanel pane1;
    private JPanel RealPlayPane;
    private JButton btnLogin;

    static HCNetSDK netSdkInstance = HCNetSDK.INSTANCE;


    HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo30 ;
    int lUserID;
    HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;

    public String ipAddress = "192.168.0.101";
    public String userName = "admin";
    public String password = "Temporal2020";
    public int port = 8000;

    private int realPlayId = -1; // return by NET_DVR_RealPlay_V30
    private int playbackId = -1; // return by NET_DVR_PlayBackByTime
    private int playPort = -1; // play port
    private int startChannel = 0; // start channel no
    private boolean stopPlayback = false;
    private boolean isShow = true;


    public Camaras(){
        super("Camaras");
        setContentPane(pane1);
        boolean initSuc = netSdkInstance.NET_DVR_Init();
        System.out.println("se inicializo el sdk: "+ initSuc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void login(){

        deviceInfo30 = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
        System.out.println("device info v30: "+ deviceInfo30);

        lUserID = netSdkInstance.NET_DVR_Login_V30(ipAddress,(short) port, userName, password, deviceInfo30);
        System.out.println("Id camaras: "+ lUserID);
        if (lUserID == -1) {
            System.out.println("NET_DVR_Login is failed!Err: "
                    + netSdkInstance.NET_DVR_GetLastError());
        }

    }
}