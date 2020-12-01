package Vistas;

import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.RealPlayCallBack;
import com.hikvision.netsdk.NET_DVR_JPEGPARA;

import javax.swing.*;


public class Camaras extends JFrame {
    private JPanel pane1;
    private JPanel realPlayPane;


    private final HCNetSDK netSdkInstance = HCNetSDK.getInstance();
    private final NET_DVR_DEVICEINFO_V30 deviceInfo30 = new NET_DVR_DEVICEINFO_V30();


    public String ipAddress = "192.168.0.102";
    public String userName = "admin";
    public String password = "Temporal2020";
    public Integer port = 8000;

    int loginId = -1;
    private int realPlayId = -1; // return by NET_DVR_RealPlay_V30
    private int playbackId = -1; // return by NET_DVR_PlayBackByTime
    private int playPort = -1; // play port
    private int startChannel = 0; // start channel no
    private boolean stopPlayback = false;
    private boolean isShow = true;

    private INT_PTR error;

    public Camaras(){
        super("Camaras");
        setContentPane(pane1);
        login();
    }

    public void login(){
        loginId = netSdkInstance.NET_DVR_Login_V30(ipAddress, port, userName, password, deviceInfo30);
        System.out.println("Id camaras: "+ loginId);
        if (loginId < 0) {
            error = new INT_PTR();
            error.iValue = netSdkInstance.NET_DVR_GetLastError();
            System.out.println("NET_DVR_Login is failed!Err: "
                    + netSdkInstance.NET_DVR_GetErrorMsg(error));
        }

    }
}
