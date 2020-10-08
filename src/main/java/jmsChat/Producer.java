package jmsChat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	public static void main(String[] args) throws JMSException {
		ConnectionFactory cf  = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = cf.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Destination destination = session.createQueue("lyes.queue");
		Destination destination = session.createTopic("rachid.topic");
		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		TextMessage textMessage = session.createTextMessage();
		textMessage.setText("Hello i'm lyes, how are you doing ?");
		messageProducer.send(textMessage);
		System.out.println("Envoi du message");
		session.close();
		connection.close();
	}

}
