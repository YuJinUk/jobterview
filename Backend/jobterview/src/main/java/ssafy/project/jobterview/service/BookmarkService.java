package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Bookmark;

public interface BookmarkService {

    //즐겨찾기 추가
    Bookmark add(Long roomId, Long memberId);

    //즐겨찾기 취소
    void delete(Long roomId, Long memberId);

    //해당 Member의 즐겨찾기 목록 조회
    Page<Bookmark> findByMember(Long memberId, Pageable pageable);
}
