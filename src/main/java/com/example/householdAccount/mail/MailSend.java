package com.example.householdAccount.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;
@Component
public class MailSend {
	
	public boolean reIssuePasswordMail(String mailAddressTo,String userName) {
		try {
			Properties property = new Properties();
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.auth", "true");
			property.put("mail.smtp.starttls.enable", "true");
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.port", "587");
			property.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(property, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("household.infomation@gmail.com", "uouqueqyqfqskuso");
				}
			});
			
			MimeMessage mimeMessage = new MimeMessage(session);
			InternetAddress toAddress = new InternetAddress(mailAddressTo, userName);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
			InternetAddress fromAddress = new InternetAddress("household.infomation@gmail.com", "家計簿WEBシステム運営");
			mimeMessage.setFrom(fromAddress);
			mimeMessage.setSubject("パスワード再発行について", "ISO-2022-JP");
			String body = userName+"様\r\n\r\n"
					+ ""
					+ "家計簿記録システムのご利用ありがとうございます。\r\n"
					+ "以下パスワードにてログインして下さい。\r\n"
					+ "また、ログイン後にユーザ情報変更画面にて\r\nパスワードの変更が可能です。\r\n\r\n"
					+ ""
					+ "再発行パスワード:"+"新規で発行したパスワードを入れる";
			mimeMessage.setText(body, "ISO-2022-JP");
			Transport.send(mimeMessage);
			System.out.println("メール送信が完了しました。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return true;
	}
}
