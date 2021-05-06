package springBootWithKafka.kafka;

import springBootWithKafka.transaction.Transaction;
import springBootWithKafka.transaction.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import java.io.IOException;

@Service
public class Consumer {
    @Autowired
    TransactionService transactionService;

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "transactions", groupId = "group_id", properties = {"enable.auto.commit = false", "auto.offset.reset = earliest"},
            topicPartitions = @TopicPartition(topic = "transactions",partitionOffsets =
                    {@PartitionOffset(partition = "0", initialOffset = "0")}))
    public void consume(String message) throws IOException {
        Transaction transaction = new Gson().fromJson(message, Transaction.class);
        Transaction added = transactionService.addNewTransaction(transaction);


        ObjectMapper mapper = new ObjectMapper();
        message = mapper.writeValueAsString(added);

        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
