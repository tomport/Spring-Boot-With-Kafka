package springBootFinanceKafkaService.transaction;

import javax.persistence.*;
import java.util.Random;


public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long transactionId;
    private Long amount;

    public Transaction(){
    }
    public Transaction(Long amount){
        this.transactionId = Math.abs(new Random().nextLong());
        this.amount = amount;
    }
    public Transaction(Long transactionId, Long amount){
        this.transactionId = transactionId;
        this.amount = amount;
    }
    public Long getTransactionId() {
        return this.transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAmount() {
        return this.amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
