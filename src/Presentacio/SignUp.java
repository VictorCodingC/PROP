package Presentacio;

import Domini.ControladorUsuari;
import Domini.Usuari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    private JButton button_msg;
    private JPanel panelMain;
    private JTextField userName;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    private ControladorUsuari controladorUsuari = new ControladorUsuari();

    public SignUp() {
        button_msg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userName.getText();
                String pss = String.valueOf(passwordField1.getPassword());
                String pss2 = String.valueOf(passwordField2.getPassword());
                if(!pss.equals(pss2))JOptionPane.showMessageDialog(null, "contrasenyes no coincideixen");
                else {
                    Usuari usuari  = controladorUsuari.insertarUsuari(username, pss);
                    JOptionPane.showMessageDialog(null, "Usuari creat!");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SignUp");
        frame.setContentPane(new SignUp().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
