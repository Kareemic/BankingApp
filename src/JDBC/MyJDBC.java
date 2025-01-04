package JDBC;
import GUI.LoginFrame;
import GUI.RegisterFrame;
import Transactions.Transaction;
import Transactions.TransactionType;
import User.User;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class MyJDBC {

    private static final String DB_URL="jdbc:mysql://127.0.0.1:3306/login";
    private static final String DB_user="root";
    private static final String DB_password="kompjuter123";

    public static User validateLogin(String username,String password){
        try{
            Connection connection= DriverManager.getConnection(DB_URL,DB_user,DB_password);

            PreparedStatement preparedStatement=connection.prepareStatement(
                    "SELECT * FROM login.login_table WHERE username = ? AND PASSWORD = ?"
            );
            preparedStatement.setString(1,username);
           preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                int userID=resultSet.getInt("id");
                BigDecimal currentBalance=resultSet.getBigDecimal("current_balance");

                return new User(userID,username,password,currentBalance);
            }
            else System.out.println("User doesn't exist");

        }
        catch(Exception e){

            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }

        return null;
    }


    public static void RegisterUser(String username,String password){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "INSERT INTO login.login_table (username,password,current_balance) values (?,?,0)"
            );
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }
    }

    public static int validateUser(String username){

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM login.login_table WHERE USERNAME = ?"
            );
            preparedStatement.setString(1,username);


            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }

        return -1;

    }



    public static void UpdateBalance(int id,BigDecimal amount){

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "UPDATE login.login_table SET current_balance=? WHERE ID = ?"
            );
            preparedStatement.setString(1,amount.toString());
            preparedStatement.setString(2,String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }

    }

    public static BigDecimal getBalance(int id){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM login.login_table WHERE ID = ?"
            );
            preparedStatement.setString(1,String.valueOf(id));

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getBigDecimal("current_balance");
            }

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }
        return null;
    }


    public static void insertTransaction(Transaction transaction){

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "INSERT INTO login.transactions (transaction_amount,transaction_date,transaction_type,user_id) VALUES (?,?,?,?)"
            );
            preparedStatement.setString(1,transaction.getTransaction_amount().toString());
            preparedStatement.setString(2,transaction.getTransaction_date().toString());
            preparedStatement.setString(3,transaction.getTransaction_type().toString());
            preparedStatement.setString(4,String.valueOf(transaction.getUser_id()));


            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }

    }

    public static Transaction[] getTransactions(int id){

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_user, DB_password);

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "SELECT * FROM login.transactions WHERE user_id=?"
            );
            preparedStatement.setString(1,String.valueOf(id));


           ResultSet resultSet=preparedStatement.executeQuery();
           List<Transaction> transactions=new ArrayList<>();
           while(resultSet.next()) {
               int tempID=resultSet.getInt("id");
               BigDecimal amount=resultSet.getBigDecimal("transaction_amount");
               Timestamp timestamp=resultSet.getTimestamp("transaction_date");
               String typeString=resultSet.getString("transaction_type");
               TransactionType type=TransactionType.valueOf(typeString);

                LocalDateTime date=timestamp.toLocalDateTime();
                date.format(Transaction.formatter);
               Transaction tempTransaction = new Transaction(amount,date,type,id);
               transactions.add(tempTransaction);
           }
           return transactions.toArray(new Transaction[0]);

        } catch (Exception e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }
        return  null;
    }
}
