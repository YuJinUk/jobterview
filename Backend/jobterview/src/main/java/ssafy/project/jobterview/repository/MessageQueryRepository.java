package ssafy.project.jobterview.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Message;

import java.util.List;

public class MessageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public MessageQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable, Message m){
        QMessage qMessage = QMessage.message;
        List<Message> messageList = queryFactory.selectFrom(qMessage)
                .where(qMessage.fromMemberVisible.eq(true))
                .where(qMessage.receiver.nickname.eq(m.getReceiver().getNickname()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qMessage.count())
                .from(qMessage)
                .where(qMessage.fromMemberVisible.eq(true))
                .where(qMessage.receiver.nickname.eq(m.getReceiver().getNickname()))
                .fetchOne();

        return new PageImpl<>(messageList,pageable, count);



    }

    public Page<Message>findAllByToMemberVisibleAndSender(Pageable pageable, Message m){
        QMessage qMessage = QMessage.message;
        List<Message> messageList = queryFactory.selectFrom(qMessage)
                .where(qMessage.toMemberVisible.eq(true))
                .where(qMessage.sender.nickname.eq(m.getSender().getNickname()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qMessage.count())
                .from(qMessage)
                .where(qMessage.toMemberVisible.eq(true))
                .where(qMessage.sender.nickname.eq(m.getSender().getNickname()))
                .fetchOne();


        return new PageImpl<>(messageList,pageable, count);



    }


}
