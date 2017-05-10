package com.tesla.cloud.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Edwin.wang on 17/5/10.
 */
public class Consumer {


    private static final Logger _logger = LoggerFactory.getLogger(Producer.class);

    public static final String  QUEUES_NAME = "tesla_topic_hello";
    public static final String QUEUES_HOST = "127.0.0.1";

    public static void receivedMessages() throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(QUEUES_HOST);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUES_NAME,false,false,false,null);

        _logger.info(" Waiting for messages...");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUES_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            _logger.info("Receive a message from the queue, queues name:{} , message body: {}",QUEUES_NAME, message);
        }

    }

    public static void main(String[] args) {
        try {

            receivedMessages();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
