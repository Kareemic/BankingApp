package User;

import java.math.BigDecimal;

public class User {

    private int id;
    private String username;
    private String password;
    private BigDecimal balance;

    public User(int id, String username, String password, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
