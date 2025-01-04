package GUI;

import JDBC.MyJDBC;
import Transactions.Transaction;
import Transactions.TransactionType;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawDialog extends AbstractDialog{
    public WithdrawDialog(MainFrame main, User user) {
        super(main, user);
        this.setTitle("Withdraw");
    }

    @Override
    public void addComponents() {

        JLabel balance=new JLabel("Balance: " + "$" + this.getUser().getBalance().toString());
        balance.setBounds(40,10,200,30);
        balance.setFont(new Font("Dialogue",Font.PLAIN,20));
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(balance);

        JLabel enter=new JLabel("Enter Amount");
        enter.setBounds(40,50,200,30);
        enter.setFont(new Font("Dialogue",Font.PLAIN,20));
        enter.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(enter);

        JTextArea amount=new JTextArea();
        amount.setBounds(40,90,200,30);
        amount.setFont(new Font("Dialogue",Font.PLAIN,20));
        amount.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(amount);


        JButton button=new JButton("Withdraw");
        button.setBounds(40,200,200,30);
        button.setFont(new Font("Dialogue",Font.PLAIN,20));
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String string= amount.getText();
                    double amount=Double.parseDouble(string);
                    if(amount>getUser().getBalance().doubleValue()) throw new IllegalArgumentException();
                    if(amount==0) throw new NumberFormatException();
                    BigDecimal newBalance=getUser().getBalance().subtract(BigDecimal.valueOf(amount));
                    getUser().setBalance(newBalance);

                    MyJDBC.insertTransaction(new Transaction(BigDecimal.valueOf(amount), LocalDateTime.now(), TransactionType.WITHDRAW,getUser().getId()));

                    WithdrawDialog.this.dispose();

                    JOptionPane.showMessageDialog(WithdrawDialog.this.main,"Successfully Withdrawed");
                    main.refreshComponents();





                }
                catch (NumberFormatException NFE){
                    JOptionPane.showMessageDialog(WithdrawDialog.this,"Wrong input!");
                    amount.setText("");
                }
                catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(WithdrawDialog.this,"You can't withdraw more than you have");
                    amount.setText("");
                }
            }
        });
        this.add(button);
    }
}
