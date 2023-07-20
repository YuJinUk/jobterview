package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.dto.SaveChatDto;

public interface ChatService {
    
    //채팅 저장
    void save(SaveChatDto dto);
    
    //모든 채팅 목록 조회
    Page<Chat> findAll(Pageable pageable);

    //roomId로 채팅 목록 조회
    Page<Chat> findByRoomId(Long roomId, Pageable pageable);

    //memberId로 채팅 목록 조회
    Page<Chat> findByMemberId(Long memberId, Pageable pageable);

    //roomId와 memberId로 채팅 목록 조회
    Page<Chat> findByRoomAndMember(Long roomId, Long memberId, Pageable pageable);
}
