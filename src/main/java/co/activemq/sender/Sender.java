package co.activemq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import co.activemq.controller.PingAppController;

public class Sender {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(PingAppController.class);
	
	public void sendMessage(String destination, Object message) {
	    LOGGER.info("sending message='{}' to destination='{}'", message, destination);
	    jmsTemplate.convertAndSend(destination, message);
	  }
}
