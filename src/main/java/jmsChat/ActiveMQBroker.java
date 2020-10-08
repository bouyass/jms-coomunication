package jmsChat;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQBroker {
	
	public static void main(String[] args) throws Exception {
		
		BrokerService broker = new BrokerService();
		
		// configure the broker
		broker.setPersistent(false);
		broker.addConnector("tcp://0.0.0.0:61616");
		broker.start();
		
	}
}
