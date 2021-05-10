package springBootWithKafka.transaction;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TransactionService {

    private Map<Long, Transaction> transactions = new TreeMap<Long, Transaction>();
    private ArrayList<Long> keys = new ArrayList<Long>();

    public Transaction addNewTransaction(Transaction transaction) {
        Transaction transaction1 = new Transaction(transaction.getAmount());
        keys.add(transaction1.getTransactionId());
        transactions.put(transaction1.getTransactionId(), transaction1);
        return transaction1;
    }

    public List<Transaction> addNewTransactions(List<Transaction> transactions) {
        if(transactions.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No transactions in input.");
        }
        List<Transaction> added = new ArrayList<Transaction>();
        for(Transaction t : transactions){
            added.add(addNewTransaction(t));
        }
        return added;
    }
    public String removeTransaction(Long transactionId) {
        if (!transactions.containsKey(transactionId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction does not exist.");
        }
        transactions.remove(transactionId);
        keys.remove(transactionId);
        return "Transaction removed.";
    }

    public List<Transaction> getTransactions() {
        if(transactions.size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No transactions exist.");
        }
        List<Transaction> got = new ArrayList<Transaction>();
        for(Long l : keys){
            got.add(transactions.get(l));
        }
        return got;
    }

    public Transaction getTransactionById(Long transactionId) {
        if (!transactions.containsKey(transactionId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction does not exist.");
        }
        return transactions.get(transactionId);
    }


    public Transaction updateTransactionById(Transaction transaction, Long transactionId) {
        if(transaction.getAmount() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Amount is missing.");
        }
        if (transactions.containsKey(transactionId)) {
            transactions.remove(transactionId);
            transactions.put(transactionId, transaction);
            return getTransactionById(transactionId);
        }
        else {
            return transactions.put(transactionId, transaction);
        }
    }
}
