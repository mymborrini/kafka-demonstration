package dev.skynet.kafkaproducer.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final NewTopic skynetDemonstration2;
  private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

  public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate, NewTopic skynetDemonstration2) {
    this.kafkaTemplate = kafkaTemplate;
    this.skynetDemonstration2 = skynetDemonstration2;
  }

  public void sendMessageToTopic1(String message) {
    // As you can see spring boot create the topic for us. But in this case is the default one so it simply will have the default partition
    // Check the KafkaTopicConfig.java class to see how to create topics programmatically
    sendMessage("skynet-demonstration1", message);
  }

  public void sendMessageToTopic2(String message) {
    sendMessage("skynet-demonstration2", message);
  }

  private void sendMessage(String topic, String message) {
    CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
    future.whenComplete((result, ex) -> {
      if (ex != null) {
        log.error("Unable to send message=[{}] topic=[{}] due to: {}", message, topic ,ex.getMessage(), ex);
      } else {
        log.info("Sent message=[{}] topic=[{}] with offset=[{}]  with partition=[{}]", message,topic ,result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
      }
    });
  }
}
