package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.dto.SaveChatDto;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.ChatRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final RoomService roomService;
    private final ChatRepository chatRepository;

    /**
     * 채팅 저장
     * 
     * @param dto 저장할 채팅 정보
     */
    @Override
    public void save(SaveChatDto dto) {
        Room findRoom = roomService.findById(dto.getRoomId());
//        Member findMember = memberService.findById(dto.getMemberId());

        // 임시로 사용할 맴버
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");
        // 채팅 저장
        chatRepository.save(new Chat(findRoom, tmpMember, dto.getContent()));
    }

    /**
     * 모든 채팅 목록 조회
     * 
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Chat> 형태로 조회된 채팅 목록 반환
     */
    @Override
    public Page<Chat> findAll(Pageable pageable) {
        Page<Chat> chatPage = chatRepository.findAll(pageable);
        if(!chatPage.isEmpty()) {
            return chatPage;
        }
        throw new NotFoundException("채팅 목록이 존재하지 않습니다.");
    }

    /**
     * 특정 Room의 채팅 목록 조회
     * 
     * @param roomId Room id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Chat> 형태로 조회된 채팅 목록 반환
     */
    @Override
    public Page<Chat> findByRoomId(Long roomId, Pageable pageable) {
        Room findRoom = roomService.findById(roomId);
        List<Chat> chatList = chatRepository.findByRoom(findRoom);

        if(chatList.size() != 0) { // 채팅 목록이 존재하면
            // Page<RoomChat> 형태로 변환하여 반환
            return new PageImpl<>(chatList, pageable, chatList.size());
        }
        // 채팅 목록이 존재하지 않으면 예외 던지기
        throw new NotFoundException("조회된 채팅이 없습니다.");
    }

    /**
     * 특정 Member의 채팅 목록 조회
     * 
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Chat> 형태로 조회된 채팅 목록 반환
     */
    @Override
    public Page<Chat> findByMemberId(Long memberId, Pageable pageable) {
//        Member findMember = memberService.findById(memberId);
        //임시로 사용할 Member 객체
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");
        List<Chat> chatList = chatRepository.findByMember(tmpMember);

        if(chatList.size() != 0) { // 채팅 목록이 존재하면
            // Page<Chat> 형태로 변환하여 반환
            return new PageImpl<>(chatList, pageable, chatList.size());
        }
        // 채팅 목록이 존재하지 않으면 예외 던지기
        throw new NotFoundException("조회된 채팅이 없습니다.");
    }

    /**
     * 특정 Room에서 특정 Member의 채팅 목록 조회
     * 
     * @param roomId Room id
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Chat> 형태로 조회된 채팅 목록 반환
     */
    @Override
    public Page<Chat> findByRoomAndMember(Long roomId, Long memberId, Pageable pageable) {
        Room findRoom = roomService.findById(roomId);
//        Member findMember = memberService.findById(memberId);

        // 임시로 사용할 맴버
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");

        // 채팅 리스트 조회
        List<Chat> chatList = chatRepository.findByRoomAndMember(findRoom, tmpMember);

        if(chatList.size() != 0) { // 채팅 목록이 존재하면
            // Page<RoomChat> 형태로 변환하여 반환
            return new PageImpl<>(chatList, pageable, chatList.size());
        }
        // 채팅 목록이 존재하지 않으면 예외 던지기
        throw new NotFoundException("조회된 채팅이 없습니다.");
    }
}
