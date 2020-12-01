/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import Vistas.Camaras;
//import Vistas.Log;

import javax.swing.*;

/**
 *
 * @author w10
 */
public class Login {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //JFrame frame1 = new Log();
                //frame1.setSize(300,300);
                //frame1.setVisible(true);

                JFrame frame1 = new Camaras();
                frame1.setSize(800,700);
                frame1.setVisible(true);

            }
        });

    }
    
}
