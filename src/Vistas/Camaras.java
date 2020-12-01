package Vistas;


//import com.hikvision.netsdk.INT_PTR;
//import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
//import com.hikvision.netsdk.RealPlayCallBack;
//import com.hikvision.netsdk.NET_DVR_JPEGPARA;

import com.sun.jna.NativeLong;

import javax.swing.*;


public class Camaras extends JFrame {
    private JPanel pane1;
    private JPanel RealPlayPane;

    static HCNetSDK netSdkInstance = HCNetSDK.INSTANCE;


    HCNetSDK.NET_DVR_DEVICEINFO_V30 deviceInfo30 ;
    NativeLong lUserID;

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
        lUserID = new NativeLong(-1);
        boolean initSuc = netSdkInstance.NET_DVR_Init();
        System.out.println("se inicializo el sdk: "+ initSuc);
        login();
    }

    public void login(){

        deviceInfo30 = new HCNetSDK.NET_DVR_DEVICEINFO_V30();

        lUserID = netSdkInstance.NET_DVR_Login_V30(ipAddress,(short) port, userName, password, deviceInfo30);
        System.out.println("Id camaras: "+ lUserID);
        long userID = lUserID.longValue();
        if (userID == -1) {
            System.out.println("NET_DVR_Login is failed!Err: "
                    + netSdkInstance.NET_DVR_GetLastError());
        }

    }
}