package io.classpath.kafka.examples.consumer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import io.classpath.kafka.examples.config.AppConfig;



public class MessageConsumer {

	public static void main(String[] args) throws InterruptedException {

		Properties properties = new Properties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfig.GROUP_ID);
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVERS);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		KafkaConsumer<Integer, String> kafkaConsumer = null;

		kafkaConsumer = new KafkaConsumer<Integer, String>(properties);

		kafkaConsumer.subscribe(Arrays.asList(AppConfig.TOPIC));
		
		System.out.println("Consumer has started ::::");

		while (true) {
			ConsumerRecords<Integer, String> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
			Thread.sleep(2000);
			for (ConsumerRecord<Integer, String> record : records) {
				System.out.println("Record key :: " + record.key());
				System.out.println("Record Value :: " + record.value());
			}
		}

	}

}
