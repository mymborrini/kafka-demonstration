package dev.skynet.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic skynetDemonstration2() {
    return TopicBuilder.name("skynet-demonstration2")
            .replicas(1)
            .partitions(3)
            .build();
  }


  @Bean
  public NewTopic skynetDemonstration3() {
    return TopicBuilder.name("skynet-demonstration3")
            .replicas(1)
            // With this it will create 2 partitions instead of 3. See the docker-compose 3 is just the default
            // if you don't specify otherwise
            .partitions(2)
            .build();
  }

  @Bean
  public NewTopic skynetDemonstration4() {
    return TopicBuilder.name("skynet-demonstration4")
            .replicas(1)
            // With this it will create 6 partitions instead of 3. See the docker-compose 3 is just the default
            // if you don't specify otherwise. As you can see you have less partitions than the default one or more paritions
            // then the default one
            .partitions(6)
            .build();
  }
}
