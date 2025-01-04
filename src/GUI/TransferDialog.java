package GUI;

import JDBC.MyJDBC;
import JDBC_Exceptions.UserNotFoundException;
import Transactions.Transaction;
import Transactions.TransactionType;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferDialog extends AbstractDialog{
    public TransferDialog(MainFrame main, User user) {
        super(main, user);
        this.setTitle("Transfer");
    }

    @Override
    public void addComponents() {
        JLabel balance=new JLabel("Balance: " + "$" + this.getUser().getBalance().toString());
        balance.setBounds(40,10,200,30);
        balance.setFont(new Font("Dialogue",Font.PLAIN,20));
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(balance);

        JLabel toUser =new JLabel("Enter the username ");
        toUser.setBounds(40,50,200,30);
        toUser.setFont(new Font("Dialogue",Font.PLAIN,20));
        toUser.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(toUser);

        JTextArea toUserArea =new JTextArea();
        toUserArea.setBounds(40,80,200,30);
        toUserArea.setFont(new Font("Dialogue",Font.PLAIN,20));
        toUserArea.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(toUserArea);

        JLabel enter=new JLabel("Enter Amount");
        enter.setBounds(40,130,200,30);
        enter.setFont(new Font("Dialogue",Font.PLAIN,20));
        enter.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(enter);

        JTextArea amount=new JTextArea();
        amount.setBounds(40,160,200,30);
        amount.setFont(new Font("Dialogue",Font.PLAIN,20));
        amount.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(amount);


        JButton button=new JButton("Transfer");
        button.setBounds(40,200,200,30);
        button.setFont(new Font("Dialogue",Font.PLAIN,20));
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String string= amount.getText();
                    double amount=Double.parseDouble(string);

                    int transferID= MyJDBC.validateUser(toUserArea.getText());
                    if(transferID==-1 || transferID==getUser().getId()) throw new UserNotFoundException();

                    if(amount>getUser().getBalance().doubleValue()) throw new IllegalArgumentException();
                    BigDecimal newBalance=getUser().getBalance().subtract(BigDecimal.valueOf(amount));
                    getUser().setBalance(newBalance);

                    MyJDBC.UpdateBalance(getUser().getId(),getUser().getBalance());

                    BigDecimal transferBalance=MyJDBC.getBalance(transferID);
                    if(transferBalance==null) throw new UserNotFoundException();
                    BigDecimal newTransferBalance=transferBalance.add(BigDecimal.valueOf(amount));

                    MyJDBC.UpdateBalance(transferID,newTransferBalance);
                    MyJDBC.insertTransaction( new Transaction(BigDecimal.valueOf(amount), LocalDateTime.now(), TransactionType.SENT,getUser().getId()));
                    MyJDBC.insertTransaction( new Transaction(BigDecimal.valueOf(amount), LocalDateTime.now(), TransactionType.RECEIVED,transferID));

                    TransferDialog.this.dispose();

                    JOptionPane.showMessageDialog(TransferDialog.this.main,"Successfully Transfered");
                    main.refreshComponents();





                }
                catch (NumberFormatException NFE){
                    JOptionPane.showMessageDialog(TransferDialog.this,"Wrong input!");
                    amount.setText("");
                }
                catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(TransferDialog.this,"You can't withdraw more than you have");
                    amount.setText("");
                }
                catch(UserNotFoundException UNFE){
                    JOptionPane.showMessageDialog(TransferDialog.this,"User doesn't exist");
                    toUserArea.setText("");
                }
            }
        });
        this.add(button);

    }
}
