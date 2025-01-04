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

public class RegisterFrame extends AbstractFrame{
    public RegisterFrame(String title) {
        super(title);
    }

    @Override
    protected void addComponents() {
        JLabel name=new JLabel("Register Window");
        name.setFont(new Font("Dialogue", Font.PLAIN,30));
        name.setBounds(40,10,300,40);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(name);

        JLabel username=new JLabel("Username:");
        username.setFont(new Font("Dialogue", Font.PLAIN,24));
        username.setBounds(40,60,300,30);
        this.add(username);

        JTextArea user=new JTextArea();
        user.setBounds(40,100,300,40);
        user.setBorder(BorderFactory.createLineBorder(Color.black));
        user.setFont(new Font("Dialogue", Font.PLAIN,22));
        this.add(user);



        JLabel password=new JLabel("Password:");
        password.setFont(new Font("Dialogue", Font.PLAIN,24));
        password.setBounds(40,160,300,30);
        this.add(password);

        JPasswordField pass=new JPasswordField();
        pass.setBounds(40,200,300,40);
        pass.setBorder(BorderFactory.createLineBorder(Color.black));
        pass.setFont(new Font("Dialogue", Font.PLAIN,22));
        this.add(pass);

        JLabel passwordCheck =new JLabel("Re-Type Password:");
        passwordCheck.setFont(new Font("Dialogue", Font.PLAIN,24));
        passwordCheck.setBounds(40,250,300,30);
        this.add(passwordCheck);

        JPasswordField passCheck=new JPasswordField();
        passCheck.setBounds(40,290,300,40);
        passCheck.setBorder(BorderFactory.createLineBorder(Color.black));
        passCheck.setFont(new Font("Dialogue", Font.PLAIN,22));
        this.add(passCheck);

        JButton registerButton=new JButton("Register");
        registerButton.setBounds(40,350,300,40);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setFont(new Font("Dialogue", Font.PLAIN,22));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=user.getText();
                char[] password=pass.getPassword();
                char[] passwordCheck= passCheck.getPassword();
                if(!Arrays.equals(password,passwordCheck)){
                    JOptionPane.showMessageDialog(RegisterFrame.this,"Passwords are not equal, try again");
                }else{

                    MyJDBC.RegisterUser(username,new String(password));
                    User userCheck=MyJDBC.validateLogin(username,new String(password));
                    if(userCheck!=null){
                        JOptionPane.showMessageDialog(RegisterFrame.this,"Succesfully Registered");
                        RegisterFrame.this.dispose();
                        LoginFrame loginFrame=new LoginFrame("Login");

                    }


                }


            }
        });
        this.add(registerButton);

        JLabel loginLabel=new JLabel("<html><a href=\"#\">Already have an account? Login here</a></html>");
        loginLabel.setBounds(40,400,310,40);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.setFont(new Font("Dialogue", Font.PLAIN,18));
        loginLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFrame.this.dispose();
                LoginFrame loginFrame=new LoginFrame("Login");
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
        this.add(loginLabel);
    }
}
