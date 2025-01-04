package GUI;

import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends AbstractFrame {


    public MainFrame(User user ) {
        super(user);
    }

    @Override
    protected void addComponents() {


        JLabel balanceText=new JLabel(user.getUsername()+"'s Current Balance:");
        balanceText.setBounds(40,50,300,40);
        balanceText.setFont(new Font("Dialogue",Font.PLAIN,24));
        balanceText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(balanceText);

        JTextField balance=new JTextField("$"+ user.getBalance());
        balance.setBounds(40,100,300,40);
        balance.setFont(new Font("Dialogue",Font.PLAIN,24));
        balance.setHorizontalAlignment(SwingConstants.RIGHT);
        balance.setEditable(false);
        this.add(balance);

        JButton deposit=new JButton("Deposit");
        deposit.setBounds(40,160,300,40);
        deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deposit.setFont(new Font("Dialogue",Font.PLAIN,24));
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositDialog depositDialog =new DepositDialog(MainFrame.this,user);

            }
        });
        this.add(deposit);

        JButton withdraw=new JButton("Withdraw");
        withdraw.setBounds(40,210,300,40);
        withdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdraw.setFont(new Font("Dialogue",Font.PLAIN,24));
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawDialog withdrawDialog=new WithdrawDialog(MainFrame.this,user);
            }
        });
        this.add(withdraw);

        JButton PastTransaction =new JButton("Past Transactions");
        PastTransaction.setBounds(40,260,300,40);
        PastTransaction.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PastTransaction.setFont(new Font("Dialogue",Font.PLAIN,24));
        PastTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PastTransactionsDialog(MainFrame.this,user);
            }
        });
        this.add(PastTransaction);


        JButton transfer =new JButton("Transfer");
        transfer.setBounds(40,310,300,40);
        transfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transfer.setFont(new Font("Dialogue",Font.PLAIN,24));
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferDialog transferDialog=new TransferDialog(MainFrame.this,user);
            }
        });
        this.add(transfer);


        JButton Logout =new JButton("Logout");
        Logout.setBounds(40,400,300,40);
        Logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Logout.setFont(new Font("Dialogue",Font.PLAIN,24));
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.dispose();
                LoginFrame loginFrame=new LoginFrame("Login");
                JOptionPane.showMessageDialog(loginFrame,"Successfully logged out");
            }
        });
        this.add(Logout);


    }

    public void refreshComponents(){
        this.getContentPane().removeAll();
        this.addComponents();
    }
}