package GUI;

import javax.swing.*;
import User.User;

import java.awt.*;

public abstract class AbstractDialog extends JDialog {
    private User user;
    MainFrame main;


  public AbstractDialog(MainFrame main,User user){
      this.user=user;
      this.main=main;
      initialize();
  }

  protected void initialize(){
      setSize(300,300);
      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setLayout(null);
      setLocationRelativeTo(null);
      addComponents();
      setVisible(true);


  }

  public abstract void addComponents();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}




