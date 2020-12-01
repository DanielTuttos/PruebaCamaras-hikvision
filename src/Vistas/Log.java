package Vistas;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;

public class Log extends JFrame {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private JTextField txtUser;
    private JTextField txtPass;
    private JButton btnIniciarSesion;
    private JPanel panelPrincipal;

    OkHttpClient client = new OkHttpClient();

    public Log(){
        super("Login");
        setContentPane(panelPrincipal);
        btnIniciarSesion.addActionListener(e -> {
            String usuario = txtUser.getText();
            String password = txtPass.getText();
            try {
                String objeto = new JSONObject()
                        .put("nombre", usuario)
                        .put("clave", password)
                        .toString();
                System.out.println(objeto);

                String response = inicioSesion(objeto);
                System.out.println(response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    String inicioSesion(String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://city2live.com:2053/usuario/login")
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute())  {
            return response.body().string();
        }
    }

}

//https://city2live.com:2053/usuario/login
