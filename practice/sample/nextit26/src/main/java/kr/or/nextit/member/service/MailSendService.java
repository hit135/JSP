package kr.or.nextit.member.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    private String getKey(int size) {
    	Random random = new Random();
    	String key = "";
    	for(int i=0; i< size; i++) {
    		int ran = random.nextInt(10);  //0~9
    		key += ran;
    	}
    	return  key; //return "622354";  //6개 숫자 랜덤
    	
    }
    
    public String sendAuthMail(String mail)  throws MessagingException{
        String authKey = getKey(6);
        MimeMessage mailMessage = mailSender.createMimeMessage();
        String mailContent = "인증번호:   "+authKey ;     // 메일 인증 번호 
        try {
            mailMessage.setSubject("NextIT 회원 가입 이메일 인증번호", "utf-8"); 
            mailMessage.setText(mailContent, "utf-8", "html");  
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
        	e.printStackTrace();
            return "false";
        }
          return authKey;
    }
}
