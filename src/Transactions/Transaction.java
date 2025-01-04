package Transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    private int id;
    private final BigDecimal transaction_amount;
    private final LocalDateTime transaction_date;
    private final TransactionType transaction_type;
    private final int user_id;

    public Transaction(BigDecimal transaction_amount, LocalDateTime transaction_date, TransactionType transaction_type, int user_id) {
        this.transaction_amount = transaction_amount;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getTransaction_amount() {
        return transaction_amount;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public TransactionType getTransaction_type() {
        return transaction_type;
    }

    public int getUser_id() {
        return user_id;
    }

   public static DateTimeFormatter formatter= DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");


    @Override
    public String toString() {
        return transaction_type+"{" + transaction_amount + "$, " + transaction_date.format(formatter)+"}";

    }
}
