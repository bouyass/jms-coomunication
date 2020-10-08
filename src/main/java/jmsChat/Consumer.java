package jmsChat;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class Consumer {
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws JMSException {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("code: ");
		
		String code = scanner.nextLine();
		
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		
		Connection connection = cf.createConnection();
		
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Destination destination = session.createQueue("lyes.queue");
		Destination destination = session.createTopic("rachid.topic");
		MessageConsumer messageConsumer = session.createConsumer(destination,"code='"+code+"'");
		 
		messageConsumer.setMessageListener(new MessageListener() {

		@Override
		public void onMessage(Message message) {
			
			if(message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				try {
						System.out.println(" Message reçu "+ textMessage.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			}
			 
		 });
		
	}
}
