# Kafka Commands

## Create topic
```
 bin/kafka-topics.sh --create --topic test-producer --bootstrap-server <broker-id>:9092
```
## List all the topics
`
bin/kafka-topics.sh --list --bootstrap-server <broker-id>:9092
`

## Describe topic
`
bin/kafka-topics.sh --describe --topic test-producer --bootstrap-server <broker-id>:9092
`

## Produce message
`
bin/kafka-console-producer.sh --topic test-producer --bootstrap-server <broker-id>:9092
`

## Consume message
`
bin/kafka-console-consumer.sh --topic test-producer --from-beginning --bootstrap-server <broker-id>:9092
`

## Delete topic
`
bin/kafka-topics.sh --delete --topic test-producer --bootstrap-server <broker-id>:9092
`

## Consuming messages

`
bin/kafka-console-consumer.sh --topic nse-stock-may --bootstrap-server <broker-id>:9092
`

## Lab-5 Callback producer

1. Update the Producer configuration with below values
```java
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.ACKS_CONFIG, "all");
```
2. Send the message and register the callback 
```java
    producer.send(new ProducerRecord<>(AppConfig.topicName, i, message),
         new LoggingCallback(message));
```
3. Implement the `Callback` interface and provide the implementation for `onCompletion` method
```java
        if(exception !=null){
            logger.error("Error sending message string = " + message);
        } else {
            logger.info(message + " persisted with offset " + recordMetadata.offset()
            + " and timestamp on " + new Timestamp(recordMetadata.timestamp()));
        }
```
4. Test the topics
```
bin/kafka-console-consumer.sh --topic callback-producer --bootstrap-server <broker-id>:9092 
```
