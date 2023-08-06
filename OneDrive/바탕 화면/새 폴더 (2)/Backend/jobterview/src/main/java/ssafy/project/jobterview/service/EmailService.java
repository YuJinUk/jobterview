package ssafy.project.jobterview.service;

public interface EmailService {
    String sendSimpleMessage(String to) throws Exception;

    String sendPasswordMessage(String to) throws Exception;
}
