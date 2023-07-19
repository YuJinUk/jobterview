package ssafy.project.jobterview.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.repository.RoomRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookmarkServiceImplTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        Member member1 = new Member("th1234@naver.com", "정태희", "123");
        Member member2 = new Member("dk1234@naver.com", "박대균", "123");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Room room = new Room("삼성 임원 면접 준비방, 너만 오면 바로 고!", 5);

        roomRepository.save(room);

        entityManager.flush();

        Chat chat1 = new Chat(room, member1, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat2 = new Chat(room, member1, "저는 처음이라 너무 떨리네요.");
        Chat chat3 = new Chat(room, member1, "꼭 붙었으면 좋겠습니다.");
        Chat chat4 = new Chat(room, member1, "취뽀하고 싶네요.");

        Chat chat5 = new Chat(room, member2, "안녕하세요.");
        Chat chat6 = new Chat(room, member2, "저도 처음이에요.");
        Chat chat7 = new Chat(room, member2, "저는 SK도 붙었습니다.");
        Chat chat8 = new Chat(room, member2, "삼성 별거 아니에요.");
    }
}