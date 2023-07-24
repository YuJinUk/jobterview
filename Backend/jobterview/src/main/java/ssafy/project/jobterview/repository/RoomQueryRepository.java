package ssafy.project.jobterview.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ssafy.project.jobterview.domain.QRoom;
import ssafy.project.jobterview.domain.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomQueryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * 특정 keyword를 roomName에 포함하는 Room 목록을 페이징 적용해서 조회
     * 
     * @param keyword roomName 검색어
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Room> 형태로 페이징된 Room 목록 반환
     */
    public Page<Room> searchByName(String keyword, Pageable pageable) {
        QRoom qRoom = QRoom.room;

        StringExpression nameExpression = qRoom.roomName;
        
        //Room 목록을 페이징 적용해서 조회
        List<Room> roomList = queryFactory.selectFrom(qRoom)
                .where(nameExpression.containsIgnoreCase(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Room 목록 카운트
        Long count = queryFactory.select(qRoom.count())
                .from(qRoom)
                .where(nameExpression.containsIgnoreCase(keyword))
                .fetchOne();

        //Page<Room> 형태로 변환 후 반환
        return new PageImpl<>(roomList, pageable, count);
    }
}
