package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.domain.BookmarkId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.BookmarkRepository;
import ssafy.project.jobterview.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final RoomService roomService;
    private final MemberRepository memberRepository;

    private Room findRoomById(Long roomId) {
        return roomService.findById(roomId);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 유저가 존재하지 않습니다."));
    }

    /**
     * 해당 Member가 해당 Room을 bookmark
     *
     * @param roomId Room id
     * @param memberId member id
     * @return 저장된 Bookmark 정보 반환
     */
    @Override
    public Bookmark add(Long roomId, Long memberId) {
        Room findRoom = findRoomById(roomId);
        Member findMember = findMemberById(memberId);

        if(bookmarkRepository.findByMemberAndRoom(findMember, findRoom).isEmpty()) {
            // Bookmark 저장
            Bookmark addedBookmark = bookmarkRepository.save(new Bookmark(new BookmarkId(roomId, memberId), findMember, findRoom));
            // 해당 Member의 bookmarkList에 추가
            findMember.getBookmarkList().add(addedBookmark);
            // 저장된 Bookmark 반환
            return addedBookmark;
        }
        throw new IllegalArgumentException("이미 즐겨찾기한 방입니다.");
    }

    /**
     * 해당 Member의 해당 Room 즐겨찾기 취소
     *
     * @param roomId Room id
     * @param memberId Member id
     */
    @Override
    public void delete(Long roomId, Long memberId) {
        Room findRoom = findRoomById(roomId);
        Member findMember = findMemberById(memberId);

        bookmarkRepository.findByMemberAndRoom(findMember, findRoom).ifPresent(
                bookmark -> bookmarkRepository.delete(bookmark));
    }

    /**
     * 해당 Member의 Bookmark 목록 조회
     * 
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<Bookmark> 형태로 Bookmark 목록 반환
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bookmark> findByMember(Long memberId, Pageable pageable) {
        List<Bookmark> bookmarkList = bookmarkRepository.findByMember(memberRepository.findById(memberId).get(), pageable);
        if (bookmarkList.isEmpty()) {
            throw new NotFoundException("즐겨찾기한 채팅방이 없습니다.");
        }
        return new PageImpl<>(bookmarkList, pageable, bookmarkList.size());
    }
}
