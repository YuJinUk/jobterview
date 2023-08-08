package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender emailSender;

//    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("JOBTERVIEW 회원 가입 인증을 완료해주세요");//제목

        String msgg = "";
        msgg += "<div style='margin:20px;'>";
        msgg += "<h1> 안녕하세요 잡터뷰입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>아래 링크를 클릭해 인증해주세요.<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다.<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 링크입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
//        msgg+= "CODE : <strong>";
//        msgg+= ePw+"</strong><div><br/> ";
        msgg += "<strong><a href=\"http://localhost:8081/emailauth/" + to + "\">인증하기</a></strong></div><br/>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("jobterview1.gmail.com", "JOBTERVIEW"));//보내는 사람

        return message;
    }

    //    public static String createKey() {
//        StringBuffer key = new StringBuffer();
//        Random rnd = new Random();
//
//        for (int i = 0; i < 8; i++) { // 인증코드 8자리
//            int index = rnd.nextInt(3); // 0~2 까지 랜덤
//
//            switch (index) {
//                case 0:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
//                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
//                    break;
//                case 1:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
//                    //  A~Z
//                    break;
//                case 2:
//                    key.append((rnd.nextInt(10)));
//                    // 0~9
//                    break;
//            }
//        }
//        return key.toString();
//    }
    @Override
    public String sendSimpleMessage(String to) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try {//예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return "SUCCESS";
    }


    private MimeMessage createPasswordMessage(String to) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("JOBTERVIEW 비밀번호 재설정 인증을 완료해주세요");//제목
        String msgg = "";
        msgg += "<div style='margin:20px;'>";
        msgg += "<h1> 안녕하세요 잡터뷰입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>아래 링크를 클릭해 인증해주세요.<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다.<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>비밀번호 재설정 인증 링크입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
//        msgg+= "CODE : <strong>";
//        msgg+= ePw+"</strong><div><br/> ";
        msgg += "<strong><a href=\"http://localhost:8081/member/resetPassword/" + to + "\">인증하기</a></strong></div><br/>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("jobterview1.gmail.com", "JOBTERVIEW"));//보내는 사람

        return message;
    }

    @Override
    public String sendPasswordMessage(String to) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createPasswordMessage(to);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return "SUCCESS";
    }


}
