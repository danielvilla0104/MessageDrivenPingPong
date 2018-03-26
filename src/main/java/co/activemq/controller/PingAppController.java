package co.activemq.controller;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.MessageNotWriteableException;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import co.activemq.pojo.PongMessage;
import co.activemq.sender.Sender;

@RestController
public class PingAppController {

	  @Autowired
	  private JmsTemplate jmsTemplate;
	  
	  @Autowired
	  Sender sender;
	  
	  @RequestMapping(value ="/sendpingmessage/{msg}", method = RequestMethod.GET)
	  public String sendPingMesage(@PathVariable(value="msg") String incomingMsg) throws MessageNotWriteableException {
		  final ActiveMQTextMessage msg = new ActiveMQTextMessage();
		  final String correlationId = UUID.randomUUID().toString();
		  msg.setText(incomingMsg);
		  msg.setCorrelationId(correlationId);
		  //Aquí finaliza el envío del mensaje
		  PongMessage pong = (PongMessage) jmsTemplate.receiveSelectedAndConvert("outbound.topic", "JMSCorrelationID='"+correlationId+"'") ;
		  return "Message OK";
	  }
	  
	  
	  @RequestMapping(value ="/sendasyncpingmessage/{msg}", method = RequestMethod.GET)
	  public DeferredResult<String> sendAsyncPingMesage(@PathVariable(value="msg") String incomingMsg) throws MessageNotWriteableException {
		  DeferredResult<String> result = new DeferredResult<>();
		  final ActiveMQTextMessage msg = new ActiveMQTextMessage();
		  final String correlationId = UUID.randomUUID().toString();
		  msg.setText(incomingMsg);
		  msg.setCorrelationId(correlationId);
		  System.out.println("el ping Id es " + msg.getCorrelationId());
		  sender.sendMessage("inbound.topic", msg);
		  //Aquí finaliza el envío del mensaje
		  
		  
		  AtomicInteger counter = new AtomicInteger(0);
		  new Thread(() -> {
			  
			  jmsTemplate.receiveSelectedAndConvert("outbound.topic", "JMSCorrelationID='"+correlationId+"'");
		      result.setResult("Message OK");
		  }, "MyThread-" + counter.incrementAndGet()).start();

		  return result;

	  }
	  
}
