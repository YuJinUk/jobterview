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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final RoomService roomService;
    private final MemberService memberService;

    /**
     * 해당 Member가 해당 Room을 bookmark
     *
     * @param roomId Room id
     * @param memberId member id
     * @return 저장된 Bookmark 정보 반환
     */
    @Override
    public Bookmark add(Long roomId, Long memberId) {
        Room findRoom = roomService.findById(roomId);
//        Member findMember = memberService.findById(memberId);

        // 임시로 사용할 맴버
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");
        
        // Bookmark 저장
        Bookmark addedBookmark = bookmarkRepository.save(new Bookmark(new BookmarkId(roomId, memberId), tmpMember, findRoom));
        // 해당 Member의 bookmarkList에 추가
        tmpMember.getBookmarkList().add(addedBookmark);
        // 저장된 Bookmark 반환
        return addedBookmark;
    }

    @Override
    public void delete(Long roomId, Long memberId) {
        Room findRoom = roomService.findById(roomId);
//        Member findMember = memberService.findById(memberId);

        // 임시로 사용할 맴버
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");

        Bookmark findBookmark = bookmarkRepository.findByMemberAndRoom(tmpMember, findRoom);
        bookmarkRepository.delete(findBookmark);
    }

    @Override
    public Page<Bookmark> findByMember(Long memberId, Pageable pageable) {
//        Member findMember = memberService.findById(memberId);

        // 임시로 사용할 맴버
        Member tmpMember = new Member("th1563@naver.com", "정태희", "123");
        List<Bookmark> bookmarkList = bookmarkRepository.findByMember(tmpMember, pageable);
        if(!bookmarkList.isEmpty()) {
            return new PageImpl<>(bookmarkList, pageable, bookmarkList.size());
        }
        throw new NotFoundException("즐겨찾기한 채팅방이 없습니다.");
    }
}
