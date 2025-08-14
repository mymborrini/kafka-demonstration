package dev.skynet.kafkaproducer.controller;

import dev.skynet.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/producer-app")
public class EventController {

  private final KafkaMessagePublisher publisher;


  public EventController(KafkaMessagePublisher publisher) {
    this.publisher = publisher;
  }


  @GetMapping("/publish/{message}")
  public ResponseEntity<?> publishMessage(@PathVariable String message) {
    try {
      publisher.sendMessageToTopic1(message);
      return ResponseEntity.ok("Message published successfully....");
    } catch (Exception ex){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
  }

  @GetMapping("/publish/batch")
  public ResponseEntity<?> publishMessageBatch() {

    Random random = new Random();
    try {
      for (int i = 0; i < 10000; i++) {
        String message = "Welcome " +  random.nextInt(100) + " message: " + i;
        publisher.sendMessageToTopic2(message);
      }
      return ResponseEntity.ok("Messages published successfully....");
    } catch (Exception ex){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
  }
}
