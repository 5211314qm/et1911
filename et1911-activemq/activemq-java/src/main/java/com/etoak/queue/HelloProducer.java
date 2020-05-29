package com.etoak.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloProducer {

	public static void main(String[] args) throws Exception {
		//1、创建ConnectionFactory()
		ConnectionFactory factory = new 
				ActiveMQConnectionFactory(null,null,"tcp://localhost:61616");
		
		//2、通过ConnectionFactory创建Connection
		Connection connection = factory.createConnection();
		connection.start();
		
		//3、通过Connection创建Session(是否开启事务，客户端签收消息的方式)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//4、通过Session创建Destination
		//如果队列不存在，nameactivemq就会自动创建
		Queue helloQueue = session.createQueue("hello1");
		
		//5、通过Session创建消息生产者
		MessageProducer producer = session.createProducer(helloQueue);
		
		//6、通过Session创建消息
		TextMessage msg = session.createTextMessage("这是第一个Hello Word消息");
		
		//7、使用消息发送者发送消息
		producer.send(msg);
		
		producer.close();
		session.close();
		connection.close();
		System.out.println("消息发送完成！");
		
	}

}

