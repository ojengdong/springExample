package kr.kdata.email.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.kdata.email.vo.MailVO;

public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	private MimeMessage mimeMessage;
	private MimeMessageHelper mimeMessageHelper;
	
//	인스턴스 초기화 블록 : 객체가 생성될 때 자동으로 실행되는 블록
//						생성자보다도 먼저 변수를 초기화할 때 사용
	
	{
		
	}
	
	
//	생성자로 직접 주입하고 나머지 변수를 초기화 한다.
	public MailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
		try{
			mimeMessage = javaMailSender.createMimeMessage();
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
//	여기에 메일을 보내는 메세지를 필요한 만큼 만들어 준다.
//	보내는 주소만 받는 메세지
	public boolean mailSend(String to) {
		boolean result = false;
		try {
			mimeMessageHelper.setFrom("emzk0799@gmail.com", "최고관리자");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(to + "님 회원가입을 축하합니다.");
			mimeMessageHelper.setText(to + "님 안녕하십니까", true);
			
			javaMailSender.send(mimeMessage);
			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

//	보내는 주소와 제목을 받는 메세지
	public boolean mailSend(String to,String subject) {
		boolean result = false;
		try {
			mimeMessageHelper.setFrom("emzk0799@gmail.com", "최고관리자");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(to + "님 회원가입을 축하합니다.");
			mimeMessageHelper.setText(to + "님 안녕하십니까", true);
			
			javaMailSender.send(mimeMessage);
			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
//	보내는 주소와 제목을 받는 메세지
	public boolean mailSend(String to,String subject, String text) {
		boolean result = false;
		try {
			mimeMessageHelper.setFrom("emzk0799@gmail.com", "최고관리자");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(to + "님 회원가입을 축하합니다.");
			mimeMessageHelper.setText(to + "님 안녕하십니까", true);
			
			javaMailSender.send(mimeMessage);
			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
//	보내는 주소와 제목을 받는 메세지
	public boolean mailSend(MailVO vo) {
		boolean result = false;
		try {
			mimeMessageHelper.setFrom("emzk0799@gmail.com", "최고관리자");
			mimeMessageHelper.setTo(vo.getTo());
			mimeMessageHelper.setSubject(vo.getSubject());
			mimeMessageHelper.setText(vo.getContent(), true);
			
			javaMailSender.send(mimeMessage);
			result = true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}

