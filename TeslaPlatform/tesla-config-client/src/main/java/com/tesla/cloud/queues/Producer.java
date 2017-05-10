package com.tesla.cloud.queues;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Edwin.wang on 17/5/10.
 */
public class Producer {

    private static final Logger _logger = LoggerFactory.getLogger(Producer.class);

    public static final String  QUEUES_NAME = "tesla_topic_hello";
    public static final String QUEUES_HOST = "127.0.0.1";

    public static void sendMessages(int index) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost(QUEUES_HOST);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUES_NAME,false,false,false,null);

        String msg = "hello world! -->" + index ;

        channel.basicPublish("",QUEUES_NAME,null,msg.getBytes());

        //channel.basicPublish("",QUEUES_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());

        _logger.info("Send a message to queues, queues name:{} , message body: {} ",QUEUES_NAME, msg);

        channel.close();

        connection.close();

    }

    public static void main(String[] args) {

        try {
            int index = 0;
            while(true) {
                sendMessages(index);
                index ++;
                TimeUnit.SECONDS.sleep(3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
