package springBootFinanceKafkaService.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transactions")
    private Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addNewTransaction(transaction);
    }
    @PostMapping("/transactions/multiple")
    private List<Transaction> addTransactions(@RequestBody List<Transaction> transactions) {
        return transactionService.addNewTransactions(transactions);
    }
    @DeleteMapping("/transactions/{transactionId}")
    private String deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.removeTransaction(transactionId);
    }
    @GetMapping("/transactions")
    private List<Transaction> getAllTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("/transactions/{transactionId}")
    private Transaction getById(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }
    @PutMapping("/transactions/{transactionId}")
    private Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable Long transactionId) {
        return transactionService.updateTransactionById(transaction, transactionId);
    }
}
