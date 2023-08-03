package ssafy.project.jobterview.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.repository.BookmarkRepository;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.repository.RoomRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("즐겨찾기 API 테스트")
class BookmarkServiceImplTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    RoomRepository roomRepository;

    static Member m1,m2,m3,m4,m5, m6;
    static Room r1,r2, r3;

    @BeforeEach
    void setUp() {
        Member member1 = new Member("jth1234@naver.com", "태희", "123");
        Member member2 = new Member("pdk1234@naver.com", "대균", "123");
        Member member3 = new Member("ksc1234@naver.com", "수창", "123");
        Member member4 = new Member("lsw1234@naver.com", "상우", "123");
        Member member5 = new Member("yjw1234@naver.com", "진욱", "123");
        Member member6 = new Member("lkh1234@naver.com", "경호", "123");

        m1 = memberRepository.save(member1);
        m2 = memberRepository.save(member2);
        m3 = memberRepository.save(member3);
        m4 = memberRepository.save(member4);
        m5 = memberRepository.save(member5);
        m6 = memberRepository.save(member6);

        Room room1 = new Room("삼성 면접 준비방, 너만 오면 바로 고!", 5);
        Room room2 = new Room("SK 면접 준비방, 너만 오면 바로 고!", 5);
        Room room3 = new Room("LG 면접 준비방, 너만 오면 바로 고!", 5);

        r1 = roomRepository.save(room1);
        r2 = roomRepository.save(room2);
        r3 = roomRepository.save(room3);

        bookmarkService.add(room1.getRoomId(), member1.getMemberId());
        bookmarkService.add(room2.getRoomId(), member1.getMemberId());
        bookmarkService.add(room3.getRoomId(), member1.getMemberId());
        bookmarkService.add(room3.getRoomId(), member2.getMemberId());
        bookmarkService.add(room3.getRoomId(), member3.getMemberId());
        bookmarkService.add(room3.getRoomId(), member4.getMemberId());
        bookmarkService.add(room3.getRoomId(), member5.getMemberId());
        bookmarkService.add(room3.getRoomId(), member6.getMemberId());
    }

    @Test
    @Order(4)
    @DisplayName("즐겨찾기 추가 테스트 및 전체 조회")
    void save() {
        List<Bookmark> bookmarkList = bookmarkRepository.findAll();
        assertEquals(8,bookmarkList.size());
    }

    @Test
    @Order(3)
    @DisplayName("즐겨찾기 취소 테스트")
    void delete() {
        bookmarkService.delete(r1.getRoomId(),m1.getMemberId());
        bookmarkService.delete(r3.getRoomId(),m2.getMemberId());

        List<Bookmark> bookmarkList = bookmarkRepository.findAll();
        assertEquals(6,bookmarkList.size());
    }

    @Test
    @Order(1)
    @DisplayName("특정 회원의 즐겨찾기 리스트 조회 테스트")
    void findBookmarkListByMember() {
        entityManager.flush();

        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Bookmark> m1BookmarkPage = bookmarkService.findByMember(m1.getMemberId(), pageable);
//        Page<Bookmark> m2BookmarkPage = bookmarkService.findByMember(m2.getMemberId(), pageable);

        assertEquals(3,m1BookmarkPage.getContent().size());
//        assertEquals(1,m2BookmarkPage.getContent().size());
    }
}