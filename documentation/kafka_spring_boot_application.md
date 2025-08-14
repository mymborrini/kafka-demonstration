# Kafka spring boot application

## Producer Application using Kafka & Spring Boot

We start by defining the kafka connection for the producers:

    spring:
        kafka:
            producer:
                bootstrap-servers: localhost:29092

This way we can already send messages and spring-boot will create the 
topic for us. The problem in this case is that we cannot configure the topic 
so much, for example we cannot configure the partitions a topic can have.

So we have to define a topic configuration like the one defined in `KafkaTopicConfig`

    @Bean
    public NewTopic skynetDemonstration2() {
        return TopicBuilder.name("skynet-demonstration2")
            .replicas(1)
            .partitions(3)
            .build();
    }

But we still need to define the kafkaAdmin topic to configure the address successfully `KafkaConnConfig`

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        return new KafkaAdmin(configs);
    }

When you send a message to a topic you cannot decide in which partition it goes. It is up to the kafka scheduler or Zookeeper
depending on which version you use.
    