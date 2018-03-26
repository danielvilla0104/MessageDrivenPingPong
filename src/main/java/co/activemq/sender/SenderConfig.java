package co.activemq.sender;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

  @Value("${spring.activemq.broker-url}")
  private String brokerUrl;
  @Value("${spring.activemq.user}")
  private String userName;
  @Value("${spring.activemq.password}")
  private String password;

  @Bean
  public ActiveMQConnectionFactory activeMQConnectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(brokerUrl);
    activeMQConnectionFactory.setUserName(userName);
    activeMQConnectionFactory.setPassword(password);
    activeMQConnectionFactory.setTrustAllPackages(true);
    return activeMQConnectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
	JmsTemplate template = new JmsTemplate(activeMQConnectionFactory());
	template.setPubSubDomain(true);
    return template;
  }

  @Bean
  public Sender sender() {
    return new Sender();
  }
}