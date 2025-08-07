# Introduction

## What is kafka and why we need it?

Apache kafka is an open-source distributed event streaming platform. 

Event Streaming implies to different task:
1. Creating Real-time Stream
2. Processing Real-time Stream

Distributed means you can have different kafka servers, if one server goes down; another one comes and take its place. 

We need kafka to not lose data in case our server is currently not responding.

I can have different applications:
 - Frontend
 - Hadoop
 - Database Slave
 - Chat Server
 - Database Server
 - Security Systems
 - Real Time monitoring
 - Data Warehouse

Every one of them need to communicate with each other. The data format can vary a lot (json, plaintext, html etc...), the connection type can
vary a lot as well (grpc, udp, rest...) and in the end you have to maintain a lot of number of connections. How to manage? Using kafka in the middle

![img.png](./images/introduction/kafka_intro.png)


So let's see some names that kafka use:

The *publisher* send a *message/event* to the *message broker*. The *subscriber* will go to that particular message broker and take the message. 

## Kafka components and Internal Architecture

The following are the components kafka is made of:
- Producer
- Consumer
- Broker
- Cluster
- Topic
- Partitions
- Offset
- Consumer Groups
- Zookeeper

>> The kafka broker is nothing but just a server. In simple word, A broker is just an intermediate entity that helps in message exchanges 
> between a producer and a consumer

The Kafka cluster is the group of all the brokers. Generally you install a broker per server so a kafka cluster is similar to a server cluster.


If you then image the broker as a database where message is placed waiting to be consumed, a topic is like a table of that database.
Is a way to categorize different types of messages. We can tell the consumer to listen to particular topic, the same way you do a sql select query 

    select * from topic_1;

And get all the message you need.

>> The kafka topic specifies the category of the message or the classification of the message. Listeners can then just respond to the messages
> that belong to the topics they are listening on.


Like sql table even topic can be partitioned. Since there can be millions of messages you have to store partitions helps you in that way
Partitions will give you better performance and availability. In the configuration of a topic we can specify the max number a topic partitions
can have. 

As soon as a message is stored in a partition a specific number is assigned to that message. This is called *offset*. Consider that the offset is 
unique per partition. So you can have the same offset in different partitions. Offset are useful to know how many message a consumer has consumed. For example,
consider that a consumer after reading 4 message went down. Kafka save that the particular consumer has consumed messages with offset 0,1,2,3. So
when the consumer will be up again, kafka will send him the message with offsets: 4,5 not 0,1,2,3 since this 4 messages are already be consumed by that consumer.


Now who listen to these partitions? If we have a single consumer who read all the messages for all the partitions, this could lay to performance issue. 
It would be wise to have, if you have 3 partitions, 3 consumer. I then can group all 3 consumer in a consumer group. Now I can split the workload between this 
consumers. The consumer register to a topic but every consumer can listen to any partitions. So the coordinator is responsible to match consumers with partitions.

>> If you have 4 consumer for 3 partitions, the 4th consumer will simply sit and wait, because the coordinator cannot match it to any partitions. In other word the coordinator
> can assign multiple partitions to a single consumer, but not multiple consumer to a single partitions. Nevertheless, if a consumer goes down consumer
> 4th will get its chance and connect to a partition


Zookeeper is a prerequisite for kafka. Kafka is a distributed system and it uses Zookeeper for coordination and to track the status of kafka cluster nodes.
It also keeps track of kafka topics, partitions, offset etc... So in another word is the "admin database" that you can find in psql or mysql. It will keep tracks
of tables, partitions, broker, statistics etc... It's the manager for your kafka cluster

## Different ways to install kafka

We have different versions of Kafka:

- Open Source: Apache kafka
- Commercial distribution: ConfluentInc Kafka
- Managed kafka service: ConfluentInc & AWS

It's a good idea to install also [kafka offset explorer](https://kafkatool.com/download.html) which will help us to monitor our kafka messaging systems. To install kafka we need to things:
- Kafka 
- Zookeeper

I will use the commercial distribution ConfluentIng Kafka

## Kafka using cli