package com.swjtu.aroundyou.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * 邮件发送工具类
 * 
 *
 */
public class MailUitl {
	/**
	 * 发送邮件的方法
	 * @param to	:收件人
	 * @param code	:激活码
	 */
	private static Logger logger = Logger.getLogger(MailUitl.class);
	public static void sendMail(String to,String mailContent){
		/**
		 * 1.获得一个Session对象.
		 * 2.创建一个代表邮件的对象Message.
		 * 3.发送邮件Transport
		 */
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "service");
			}
			
		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		// 设置发件人:
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC   密送BCC
			// 设置标题
			message.setSubject("AroundYou 提示消息");
			// 设置邮件正文:
			message.setContent(mailContent, "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
			logger.info("send mail to "+to);
		} catch (AddressException e) {
			logger.error("send mail to "+to+" failed");
		} catch (MessagingException e) {
			logger.error("send mail to "+to+" failed");
		}	
	}
	
}
