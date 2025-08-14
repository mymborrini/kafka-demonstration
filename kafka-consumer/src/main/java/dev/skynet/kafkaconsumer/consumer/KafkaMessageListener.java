package dev.skynet.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  // You can define the group id both in this annotation and in the application.yml
  @KafkaListener(
          topics = "skynet-demonstration1",
          groupId = "skynet-group-1"
  )
  public void consume(String message){
    logger.info("Consumer consume the message {}",message);
  }

}
