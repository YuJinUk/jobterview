package ssafy.project.jobterview.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.dto.SaveChatDto;
import ssafy.project.jobterview.repository.ChatRepository;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.repository.RoomRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("채팅 API 테스트")
class ChatServiceImplTest {

    static Long mId1, mId2, rId1, rId2;
    static Pageable pageable;
    @Autowired
    EntityManager entityManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ChatService chatService;
    @Autowired
    ChatRepository chatRepository;

    @BeforeEach
    void setUp() {
        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        pageable = PageRequest.of(page, size, sort);

        Member member1 = new Member("th1234@naver.com", "정태희", "123");
        Member member2 = new Member("dk1234@naver.com", "박대균", "123");

        Long memberId1 = memberRepository.save(member1).getMemberId();
        Long memberId2 = memberRepository.save(member2).getMemberId();

        Room room1 = new Room("삼성 임원 면접 준비방, 너만 오면 바로 고!", 5);
        Room room2 = new Room("SK 임원 면접 준비방, 너만 오면 바로 고!", 5);
        Long roomId1 = roomRepository.save(room1).getRoomId();
        Long roomId2 = roomRepository.save(room2).getRoomId();

        Member findMember1 = memberRepository.findById(memberId1).get();
        Member findMember2 = memberRepository.findById(memberId2).get();

        mId1 = findMember1.getMemberId();
        mId2 = findMember2.getMemberId();

        Room findRoom1 = roomRepository.findById(roomId1).get();
        Room findRoom2 = roomRepository.findById(roomId2).get();

        rId1 = findRoom1.getRoomId();
        rId2 = findRoom2.getRoomId();

        Chat chat5 = new Chat(findRoom1, findMember1, "안녕하세요.");
        Chat chat1 = new Chat(findRoom1, findMember1, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat2 = new Chat(findRoom1, findMember1, "저는 처음이라 너무 떨리네요.");
        Chat chat3 = new Chat(findRoom1, findMember1, "꼭 붙었으면 좋겠습니다.");
        Chat chat4 = new Chat(findRoom2, findMember1, "취뽀하고 싶네요.");

        Chat chat6 = new Chat(findRoom1, findMember2, "저도 처음이에요.");
        Chat chat7 = new Chat(findRoom2, findMember2, "저는 SK도 붙었습니다.");
        Chat chat8 = new Chat(findRoom2, findMember2, "삼성 별거 아니에요.");

        chatRepository.save(chat1);
        chatRepository.save(chat2);
        chatRepository.save(chat3);
        chatRepository.save(chat4);
        chatRepository.save(chat5);
        chatRepository.save(chat6);
        chatRepository.save(chat7);
        chatRepository.save(chat8);
    }

    @Test
    @DisplayName("특정 Room의 채팅 조회")
    void findByRoomId() {
        Page<Chat> room1Chat = chatService.findByRoomId(rId1, pageable);
        Page<Chat> room2Chat = chatService.findByRoomId(rId2, pageable);

        assertEquals(5, room1Chat.getContent().size());
        assertEquals(3, room2Chat.getContent().size());
    }

    @Test
    @DisplayName("특정 Member의 채팅 조회")
    void findByMemberId() {
        Page<Chat> member1Chat = chatService.findByMemberId(mId1, pageable);
        Page<Chat> member2Chat = chatService.findByMemberId(mId2, pageable);

        assertEquals(5, member1Chat.getContent().size());
        assertEquals(3, member2Chat.getContent().size());
    }

    @Test
    @DisplayName("특정 Room의 특정 Member의 채팅 조회")
    void findByMemberAndRoom() {
        Page<Chat> room1Member1Chat = chatService.findByRoomAndMember(rId1, mId1, pageable);
        Page<Chat> room2Member2Chat = chatService.findByRoomAndMember(rId2, mId2, pageable);

        assertEquals(4, room1Member1Chat.getContent().size());
        assertEquals(2, room2Member2Chat.getContent().size());
    }
}