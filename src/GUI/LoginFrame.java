package GUI;

import JDBC.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import User.User;
import com.mysql.cj.log.Log;

public class LoginFrame extends AbstractFrame{



    public LoginFrame(String title) {
        super(title);
    }

    @Override
    protected void addComponents() {

        JLabel name=new JLabel("Banking Application");
        name.setFont(new Font("Dialogue", Font.PLAIN,30));
        name.setBounds(50,10,300,40);
        this.add(name);

        JLabel username=new JLabel("Username:");
        username.setFont(new Font("Dialogue", Font.PLAIN,24));
        username.setBounds(40,110,300,30);
        this.add(username);

        JTextArea user=new JTextArea();
        user.setBounds(40,150,300,40);
        user.setBorder(BorderFactory.createLineBorder(Color.black));
        user.setFont(new Font("Dialogue", Font.PLAIN,22));
        this.add(user);



        JLabel password=new JLabel("Password:");
        password.setFont(new Font("Dialogue", Font.PLAIN,24));
        password.setBounds(40,210,300,30);
        this.add(password);

        JPasswordField pass=new JPasswordField();
        pass.setBounds(40,250,300,40);
        pass.setBorder(BorderFactory.createLineBorder(Color.black));
        pass.setFont(new Font("Dialogue", Font.PLAIN,22));
        this.add(pass);

        JButton loginButton=new JButton("Login");
        loginButton.setBounds(40,350,300,40);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font("Dialogue", Font.PLAIN,22));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char[] pas=pass.getPassword();
                String password=new String(pas);


               User USER= MyJDBC.validateLogin(user.getText(), password);
               if(USER!=null){
                   LoginFrame.this.dispose();

              MainFrame main= new MainFrame(USER);
               JOptionPane.showMessageDialog(main,"Connection successful");
            }
            else{
                JOptionPane.showMessageDialog(LoginFrame.this,"Failed to connect");
            }}
        });
        this.add(loginButton);

        JLabel registerLabel=new JLabel("<html><a href=\"#\">Don't have an account? Register here</a></html>");
        registerLabel.setBounds(40,400,310,40);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setFont(new Font("Dialogue", Font.PLAIN,18));
        registerLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFrame.this.dispose();
                RegisterFrame registerFrame=new RegisterFrame("Register");


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        this.add(registerLabel);


    }
}
