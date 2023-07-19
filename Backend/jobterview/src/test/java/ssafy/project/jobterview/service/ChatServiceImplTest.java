package ssafy.project.jobterview.service;

import org.junit.jupiter.api.BeforeEach;
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
class ChatServiceImplTest {

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
        Member member1 = new Member("th1234@naver.com", "정태희", "123");
        Member member2 = new Member("dk1234@naver.com", "박대균", "123");

        Long memberId1 = memberRepository.save(member1).getMemberId();
        Long memberId2 = memberRepository.save(member2).getMemberId();

        Room room = new Room("삼성 임원 면접 준비방, 너만 오면 바로 고!", 5);
        Long roomId = roomRepository.save(room).getRoomId();

        SaveChatDto chatDto1 = new SaveChatDto(roomId, memberId1, "혹시 임원 면접 보신분 있으신가요?");
        SaveChatDto chatDto2 = new SaveChatDto(roomId, memberId1, "저는 처음이라 너무 떨리네요.");
        SaveChatDto chatDto3 = new SaveChatDto(roomId, memberId1, "꼭 붙었으면 좋겠습니다.");
        SaveChatDto chatDto4 = new SaveChatDto(roomId, memberId1, "취뽀하고 싶네요.");

        SaveChatDto chatDto5 = new SaveChatDto(roomId, memberId2, "안녕하세요.");
        SaveChatDto chatDto6 = new SaveChatDto(roomId, memberId2, "저도 처음이에요.");
        SaveChatDto chatDto7 = new SaveChatDto(roomId, memberId2, "저는 SK도 붙었습니다.");
        SaveChatDto chatDto8 = new SaveChatDto(roomId, memberId2, "삼성 별거 아니에요.");

        Member findMember1 = memberRepository.findById(memberId1).get();
        Member findMember2 = memberRepository.findById(memberId2).get();

        Room findRoom = roomRepository.findById(roomId).get();

        Chat chat1 = new Chat(findRoom, findMember1, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat2 = new Chat(findRoom, findMember1, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat3 = new Chat(findRoom, findMember1, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat4 = new Chat(findRoom, findMember1, "혹시 임원 면접 보신분 있으신가요?");

        Chat chat5 = new Chat(findRoom, findMember2, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat6 = new Chat(findRoom, findMember2, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat7 = new Chat(findRoom, findMember2, "혹시 임원 면접 보신분 있으신가요?");
        Chat chat8 = new Chat(findRoom, findMember2, "혹시 임원 면접 보신분 있으신가요?");


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
    void 채팅_등록() {
        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Chat> chatPage = chatService.findAll(pageable);
        assertEquals(8, chatPage.getContent().size());
    }
}