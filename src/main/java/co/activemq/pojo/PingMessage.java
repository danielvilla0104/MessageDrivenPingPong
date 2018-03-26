package co.activemq.pojo;

import java.util.Date;

public class PingMessage {
	//private Long id;
	private String body;
	private Date messageDate;
	
	public PingMessage() {
	}
	public PingMessage(String body, Date messageDate) {
		this.body = body;
		this.messageDate = messageDate;
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
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	@Override
	public String toString() {
		return "PingMessage [body=" + body + ", messageDate=" + messageDate + "]";
	}
	

}
