package GUI;

import javax.swing.*;
import User.User;

public abstract class AbstractFrame extends JFrame {

    protected User user;

    public AbstractFrame(String title){
        initialize(title);
    }

    public AbstractFrame(User user){
        this.user=user;
        initialize(user.getUsername()+"'s Bank App");
    }

    private void initialize(String title){

            setTitle(title);
            setResizable(false);
            setSize(400,500);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(null);
            setLocationRelativeTo(null);
            addComponents();
            setVisible(true);

    }
    protected abstract void addComponents();

}
