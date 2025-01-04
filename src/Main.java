import GUI.LoginFrame;
import GUI.MainFrame;
import GUI.RegisterFrame;
import Transactions.Transaction;
import Transactions.TransactionType;
import User.User;
import JDBC.MyJDBC;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {

    public  static void main(String[] Args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              new LoginFrame("Login");
              }





        });


    }
}
