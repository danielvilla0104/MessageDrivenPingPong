package co.activemq.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.activemq.command.ActiveMQTextMessage;

public class PongMessage extends ActiveMQTextMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String body;
	private Date messageDate;
	private String type;
	
	public PongMessage() {
	}
	public PongMessage(String body, Date message_date, String type) {
		this.body = body;
		this.messageDate = message_date;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date message_date) {
		this.messageDate = message_date;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Pong_Message [body=" + body + ", message_date=" + messageDate + ", type=" + type + "]";
	}
}