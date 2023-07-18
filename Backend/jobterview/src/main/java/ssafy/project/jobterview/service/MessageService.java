package ssafy.project.jobterview.service;

import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.domain.Question;

import java.util.List;
import java.util.Optional;

public interface MessageService {
//    Question save(Question q);
//    Question update(Question q);
//    List<Question> findAll();
//    Optional<Question> findAllByCategory(Category c);
//    Question findById(Long questionId);
//    void deleteById(Long questionId);
//    List<Question> findByRandom();
    // 쪽지 작성
    Message save(Message m);

    // 눌렀을 경우 쪽지 읽음 처리
    Message read(Long id);
    // 받은 쪽지 삭제
    Message receiveMessageDelete (Long id);
    // 보낸 쪽지 삭제
    Message sendMessageDelete(Long id);

    // 받은 쪽지 전체 보기
    Optional<Message> findAllByToMemberVisibleAndReceiver(Message m);

    // 읽은 쪽지 전체 보기
    Optional<Message> findAllByFromMemberVisibleAndSender(Message m);

    // 쪽지 상세 보기 (눌렀을 경우)
    Message findById(Long id);


}
