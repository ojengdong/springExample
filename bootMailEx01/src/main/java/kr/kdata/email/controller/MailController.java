package kr.kdata.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import kr.kdata.email.service.MailService;
import kr.kdata.email.vo.MailVO;

@Controller
@RequestMapping(value = "/mail")
public class MailController {

   @Autowired
   private JavaMailSender javaMailSender;
   
   // 1. 가장 기본적으로 메일 보내기 -- 한사람에게만 보내는 메일
   @GetMapping(value = "/mail1")
   public String mail1(Model model) {
      String result = "메일 보내기를 실패했습니다.";
      try {
         MimeMessage  message = javaMailSender.createMimeMessage();
         message.setFrom("emzk0799@gmail.com");
         message.setRecipients(RecipientType.TO, "rkdgoals20@gmail.com");
         message.setSubject("하하하하 제목이래");
         message.setText("<h1>ㅋㅋㅋ 내용이라네요</h1>");
         javaMailSender.send(message);
         result = "ithuman202303@gmail.com에게 메일 보내기를 성공했습니다.";
      }catch (Exception e) {
         e.printStackTrace();
      }
      model.addAttribute("result", result);
      return "/mail/mail1";
   }
   // 메일폼 띄우기 2
   @GetMapping(value = "/mailForm")
   public String mailForm() {
      return "/mail/mailForm";
   }
   // 폼메일 보내기 2
   @PostMapping(value = "/mail2")
   public String mail2(@ModelAttribute MailVO mailVO, Model model) {
      String result = "메일 보내기를 실패했습니다.";
      try {
//         MimeMessage  message = javaMailSender.createMimeMessage();
//         message.setFrom(mailVO.getFrom());
//         message.setRecipients(RecipientType.TO, mailVO.getTo());
//         message.setSubject(mailVO.getSubject());
//         message.setText(mailVO.getContent());
//         javaMailSender.send(message);
//         result = mailVO.getFrom()+"님이 " + mailVO.getTo() + "님에게 메일 보내기를 성공했습니다.";
    	  MailService mailService = new MailService(javaMailSender);
    	  if(mailService.mailSend(mailVO)) {
    		  result = mailVO.getFrom() + "님이" + mailVO.getTo() + "님에게 메일";
    	  }
      }catch (Exception e) {
         e.printStackTrace();
      }
      model.addAttribute("result", result);
      return "/mail/mail1";
   }
   
   // 메일폼 띄우기 2
      @GetMapping(value = "/mailForm2")
      public String mailForm2() {
         return "/mail/mailForm2";
      }
      // 폼메일 보내기 2
      @PostMapping(value = "/mail3")
      public String mail3(@ModelAttribute MailVO mailVO, Model model) {
         String result = "메일 보내기를 실패했습니다.";
         try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true ,"UTF-8");
            helper.setFrom(mailVO.getFrom(), "관리자"); //(보내는 사람이메일 , 보내는 사람 이름)
            helper.setTo(mailVO.getTo());
            helper.setSubject(mailVO.getSubject());
            helper.setText(mailVO.getContent(), true); //두번째 인수가 HTML 사용여부다.
            javaMailSender.send(message);
            result = mailVO.getFrom()+"님이 " + mailVO.getTo() + "님에게 메일 보내기를 성공했습니다.";
         }catch (Exception e) {
            e.printStackTrace();
         }
         model.addAttribute("result", result);
         return "/mail/mail1";
      }
   
}