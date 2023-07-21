//package ssafy.project.jobterview.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import ssafy.project.jobterview.domain.Message;
//import ssafy.project.jobterview.domain.QMessage;
//
//
//import java.util.List;
//
//public class MessageQueryRepository implements MessageCustomRepository{
//    private final JPAQueryFactory queryFactory;
//
//    public MessageQueryRepository(JPAQueryFactory queryFactory) {
//        this.queryFactory = queryFactory;
//    }
//
//    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable, String nickname){
//        QMessage qMessage = QMessage.message;
//        List<Message> messageList = queryFactory.selectFrom(qMessage)
//                .where(qMessage.fromMemberVisible.eq(true))
//                .where(qMessage.receiver.nickname.eq(nickname))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        Long count = queryFactory.select(qMessage.count())
//                .from(qMessage)
//                .where(qMessage.fromMemberVisible.eq(true))
//                .where(qMessage.receiver.nickname.eq(nickname))
//                .fetchOne();
//
//        return new PageImpl<>(messageList,pageable, count);
//
//
//
//    }
//
//    public Page<Message> findAllByToMemberVisibleAndSender(Pageable pageable, String nickname){
//        QMessage qMessage = QMessage.message;
//        List<Message> messageList = queryFactory.selectFrom(qMessage)
//                .where(qMessage.toMemberVisible.eq(true))
//                .where(qMessage.sender.nickname.eq(nickname))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        Long count = queryFactory.select(qMessage.count())
//                .from(qMessage)
//                .where(qMessage.toMemberVisible.eq(true))
//                .where(qMessage.sender.nickname.eq(nickname))
//                .fetchOne();
//
//
//        return new PageImpl<>(messageList,pageable, count);
//
//
//
//    }
//
//
//}

package ssafy.project.jobterview.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.domain.QMessage;


import java.util.List;

public class MessageRepositoryImpl implements MessageCustomRepository{
    private final JPAQueryFactory queryFactory;

    public MessageRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable,String nickname){
        QMessage qMessage = QMessage.message;
        List<Message> messageList = queryFactory.selectFrom(qMessage)
                .where(qMessage.fromMemberVisible.eq(true)
                        ,(qMessage.receiver.nickname.eq(nickname)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qMessage.count())
                .from(qMessage)
                .where(qMessage.fromMemberVisible.eq(true)
                        ,(qMessage.receiver.nickname.eq(nickname)))
                .fetchOne();

        return new PageImpl<>(messageList,pageable, count);



    }

    public Page<Message> findAllByToMemberVisibleAndSender(Pageable pageable, String nickname){
        QMessage qMessage = QMessage.message;
        List<Message> messageList = queryFactory.selectFrom(qMessage)
                .where(qMessage.toMemberVisible.eq(true)
                        ,(qMessage.sender.nickname.eq(nickname)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = queryFactory.select(qMessage.count())
                .from(qMessage)
                .where(qMessage.toMemberVisible.eq(true)
                        ,(qMessage.sender.nickname.eq(nickname)))
                .fetchOne();


        return new PageImpl<>(messageList,pageable, count);



    }


}

