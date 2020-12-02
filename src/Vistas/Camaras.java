package Vistas;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;

import javax.security.sasl.RealmCallback;
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

    HCNetSDK.FMSGCallBack fMSFCallBack;
    HCNetSDK.FRealDataCallBack_V30 RealData = null;

    private int realPlayId = -1; // return by NET_DVR_RealPlay_V30
    private int playbackId = -1; // return by NET_DVR_PlayBackByTime
    private int playPort = -1; // play port
    private int startChannel = 1; // start channel no
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
        if (lUserID < 0) {

            JOptionPane.showMessageDialog(null,"Login incorrecto, codigo de error: " + netSdkInstance.NET_DVR_GetLastError());
            System.out.println("NET_DVR_Login is failed!Err: "
                    + netSdkInstance.NET_DVR_GetLastError());
        }else{
            JOptionPane.showMessageDialog(null,"Login correcto, id: " + lUserID);
            preview();
        }

    }

    public void preview(){
        HCNetSDK.NET_DVR_PREVIEWINFO previewInfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
        System.out.println(RealPlayPane.getWidth());
        System.out.println(RealPlayPane.getHeight());
        HWND hwnd = new HWND(Native.getComponentPointer(RealPlayPane));
        previewInfo.hPlayWnd = hwnd;
        previewInfo.lChannel = startChannel;
        previewInfo.dwStreamType = 0;
        previewInfo.dwLinkMode = 0;
        previewInfo.bBlocked = 1;
        previewInfo.dwDisplayBufNum = 1;
        previewInfo.byProtoType = 0;
        previewInfo.byPreviewMode = 0;

        realPlayId = netSdkInstance.NET_DVR_RealPlay_V40(lUserID, previewInfo, null, null);
        System.out.println("ID de informacion real: " + realPlayId);
        if(realPlayId < 0 ){
            JOptionPane.showMessageDialog(null,"No se pudo iniciar el preview, codigo de error "
                    + netSdkInstance.NET_DVR_GetLastError());
            System.out.println("NET_DVR_Login is failed!Err: "
                    + netSdkInstance.NET_DVR_GetLastError());
            return ;
        }
    }

}