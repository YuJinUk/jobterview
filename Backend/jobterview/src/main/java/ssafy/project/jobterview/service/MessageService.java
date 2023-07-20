package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.dto.MessageDto;


public interface MessageService {

    // 쪽지 작성
    void save(MessageDto mdto);

    // 눌렀을 경우 쪽지 읽음 처리
    Message read(Long id);
    // 받은 쪽지 삭제
    void receiveMessageDelete (Long id);
    // 보낸 쪽지 삭제
    void sendMessageDelete(Long id);

    // 받은 쪽지 전체 보기
    Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable,String nickname);

    // 읽은 쪽지 전체 보기
    Page<Message> findAllByToMemberVisibleAndSender(Pageable pageable, String nickname);

    // 쪽지 상세 보기 (눌렀을 경우)
    Message findById(Long id);


}
