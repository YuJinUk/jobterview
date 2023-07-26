package ssafy.project.jobterview.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Message;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepository {
    private final EntityManager em;
    public void save(Message m){
        em.persist(m);
    }

    // 받은 쪽지함
    public List<Message>findAllByFromMemberVisibleAndSender(boolean to_member_visible, Member sender_id){
        return em.createQuery("select * from Message Where to_member_visible is true and sender_id=?");
    }

    pulbic List<Message> findfindByfrom_member_visibleAndreciver_id(boolean from_member_visible, Member reciver_id){
        return em.createQuery("select * from Message Where from_member_visible is true and reciver_id=?");
    }

    public message update




}
