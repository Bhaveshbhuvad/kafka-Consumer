package io.classpath.kafka.examples.consumer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import io.classpath.kafka.examples.config.AppConfig;



public class MessageConsumerFromPartition {
	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfig.GROUP_ID);
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVERS);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		KafkaConsumer<Integer, String> kafkaConsumer = null;

		kafkaConsumer = new KafkaConsumer<Integer, String>(properties);

		//kafkaConsumer.subscribe(Arrays.asList(AppConfig.TOPIC));
		//instead of susbcribe to a topic, you will be assigned the paritions by the kakfa
		kafkaConsumer.assign(Collections.singleton(new TopicPartition(AppConfig.TOPIC, 0)));
		
		System.out.println("Consumer has started ::::");

		while (true) {
			ConsumerRecords<Integer, String> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (ConsumerRecord<Integer, String> record : records) {
				System.out.println("Record key :: " + record.key());
				System.out.println("Record Value :: " + record.value());
				System.out.println("Parition number ::" + record.partition());
			}
		}

	}

		
	}

