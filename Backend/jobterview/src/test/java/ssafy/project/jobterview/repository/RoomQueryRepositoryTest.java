package ssafy.project.jobterview.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Room;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomQueryRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomQueryRepository roomQueryRepository;

    @BeforeEach
    void setUp() {
        //Room instance 생성
        Room room1 = new Room("1번방",5);
        Room room2 = new Room("2번방",5);
        Room room3 = new Room("3번방",5);
        Room room4 = new Room("4번방",5);
        Room room5 = new Room("5번방",5);
        Room room6 = new Room("11번방",5);
        Room room7 = new Room("12번방",5);
        Room room8 = new Room("13번방",5);

        //등록
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
        roomRepository.save(room5);
        roomRepository.save(room6);
        roomRepository.save(room7);
        roomRepository.save(room8);
    }

    @Test
    @Order(1)
    public void 방등록() {
        List<Room> roomList = roomRepository.findAll();

        assertEquals(8,roomList.size());

        Room room10 = new Room("14번방", 5);
        Room savedRoom = roomRepository.save(room10);

        assertEquals("14번방", savedRoom.getRoomName());
    }

    @Test
    @Order(2)
    public void 정렬기준으로_조회() {
        //given
        int offset = 0;
        int pageSize = 10;

        String orderByName = "roomName";
        String orderById = "roomId";
        String orderByDate = "createdDate";

        Sort.Direction asc = Sort.Direction.ASC;
        Sort.Direction desc = Sort.Direction.DESC;

        //when
        //날짜 오름차순
        List<Room> oldestDateList = roomRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(asc, orderByDate))).getContent();
        //이름 내림차순
        List<Room> descNameList = roomRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(desc, orderByName))).getContent();
        //아이디 내림차순
        List<Room> descIdList = roomRepository.findAll(PageRequest.of(offset, pageSize, Sort.by(desc, orderById))).getContent();

        //then
        assertTrue(oldestDateList.get(0).getCreatedDate()
                .isBefore(oldestDateList.get(7).getCreatedDate()));
        assertTrue((descIdList.get(0).getRoomId() > descIdList.get(1).getRoomId())
                &&(descIdList.get(1).getRoomId() > descIdList.get(2).getRoomId())
                &&(descIdList.get(2).getRoomId() > descIdList.get(3).getRoomId()));
        assertEquals("5번방",descNameList.get(0).getRoomName());

    }

    @Test
    @Order(3)
    public void 방이름으로_검색() {
        //given
        String keyword1 = "방";
        String keyword2 = "1";
        String keyword3 = "2";

        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(0, 100, sort);

        //when
//        List<Room> roomList1 = roomQueryRepository.searchByName(keyword1, pageable).getContent();
//        List<Room> roomList2 = roomQueryRepository.searchByName(keyword2, pageable).getContent();
//        List<Room> roomList3 = roomQueryRepository.searchByName(keyword3, pageable).getContent();

        List<Room> roomList1 = roomRepository.findByRoomNameContains(pageable, keyword1).getContent();
        List<Room> roomList2 = roomRepository.findByRoomNameContains(pageable, keyword2).getContent();
        List<Room> roomList3 = roomRepository.findByRoomNameContains(pageable, keyword3).getContent();

        //then
        assertEquals(8,roomList1.size());
        assertEquals(4,roomList2.size());
        assertEquals(2,roomList3.size());
    }
}