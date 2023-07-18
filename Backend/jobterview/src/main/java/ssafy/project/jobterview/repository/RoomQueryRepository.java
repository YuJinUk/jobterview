package ssafy.project.jobterview.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ssafy.project.jobterview.domain.Room;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Room> searchByName(String keyword, Pageable pageable) {
        QRoom qRoom = QRoom.room;

        StringExpression nameExpression = qRoom.roomName;

        List<Room> roomList = queryFactory.selectFrom(qRoom)
                .where(nameExpression.containsIgnoreCase(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(qRoom.count())
                .from(qRoom)
                .where(nameExpression.containsIgnoreCase(keyword))
                .fetch();

        return new PageImpl<>(roomList, pageable, count);
    }
}
