package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Bookmark;

public interface BookmarkService {

    Bookmark add(Long roomId, Long memberId);

    void delete(Long roomId, Long memberId);

    Page<Bookmark> findByMember(Long memberId, Pageable pageable);
}
