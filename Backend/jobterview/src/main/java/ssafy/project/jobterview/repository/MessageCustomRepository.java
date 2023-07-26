package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Message;

public interface MessageCustomRepository {
    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable,String nickname);
    public Page<Message> findAllByToMemberVisibleAndSender(Pageable pageable, String nickname);
}
