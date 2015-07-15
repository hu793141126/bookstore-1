package cn.mengmei.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sun.mail.util.MailSSLSocketFactory;

import cn.mengmei.domain.User;

public class SendMail extends Thread {
	
	private User user;

	public SendMail(User user){
		this.user = user;
	}

	@Override
	public void run() {
		try{
			/*Properties props = new Properties();
			props.setProperty("mail.host", "smtp.qq.com");
			props.setProperty("mail.transport.protocol", "smtp");
			
			//SSL加密,以下两种择一即可
			//props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//props.setProperty("mail.smtp.socketFactory.fallback", "false");
			//props.setProperty("mail.smtp.socketFactory.port", "465");
			
			MailSSLSocketFactory sf = new MailSSLSocketFactory();  
		    sf.setTrustAllHosts(true);  
		    props.put("mail.smtp.ssl.enable", "true");  
		    // also use following for additional safety  
		    //props.put("mail.smtp.ssl.checkserveridentity", "true");  
		    props.put("mail.smtp.ssl.socketFactory", sf);  
			
			props.setProperty("mail.smtp.auth", "true"); 
			Session session = Session.getInstance(props);*/
			
			Context initCtx = new InitialContext();                     //初始化一个JNDI
			Context envCtx = (Context) initCtx.lookup("java:comp/env"); //拿到 JNDI 里面的容器
			Session session = (Session) envCtx.lookup("mail/Session");  //再调用容器的lookup方法，把Session对象检索出来。
			
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect("smtp.qq.com", 465, "381740108@qq.com", "mengMei219meng");
			MimeMessage msg = createMessage(session,user);
			ts.sendMessage(msg, msg.getRecipients(RecipientType.TO));
			ts.close();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private MimeMessage createMessage(Session session, User user) throws Exception {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("381740108@qq.com"));
		msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
		msg.setSubject("Meta's bookstore 用户激活","UTF-8");
		
		msg.setContent("请点击<a href='http://localhost:8080/bookstore/client/ActiveServlet?verifyCode="+user.getVerifyCode()+"'>激活</a>，完成注册！", "text/html;charset=UTF-8");
		msg.saveChanges();
		return msg;
	}

	
}
