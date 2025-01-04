package GUI;

import JDBC.MyJDBC;
import Transactions.Transaction;
import User.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PastTransactionsDialog2 {

    private User user;
    private MainFrame main;

    public PastTransactionsDialog2(MainFrame main, User user) {
        this.user = user;
        this.main = main;

        JDialog dialog = new JDialog();
        dialog.setTitle(user.getUsername()+"'s Past Transactions");
        dialog.setSize(400, 300);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Create the table with sample data
        Transaction[] list = MyJDBC.getTransactions(user.getId());
        System.out.println(list.length);
        if (list.length!=0) {
            String[] columnNames = {"TYPE", "AMOUNT", "DATE"};
            String[][] data = new String[list.length][3];
            for (int i = 0; i < list.length; i++) {
                String type = list[i].getTransaction_type().toString();
                String amount = list[i].getTransaction_amount().toString();
                String date = list[i].getTransaction_date().format(Transaction.formatter);
                data[i][0] = type;
                data[i][1] = amount + "$";
                data[i][2] = date;
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setDefaultEditor(Object.class,null);

            // Add the table to a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the JScrollPane to the dialog
            dialog.add(scrollPane);

            // Make the dialog visible
            dialog.setVisible(true);
        }
        else{
            JLabel label=new JLabel("No previous transactions");
            label.setFont(new Font("Dialogue",Font.PLAIN,30));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            dialog.add(label);
            dialog.setVisible(true);
        }
    }
}









