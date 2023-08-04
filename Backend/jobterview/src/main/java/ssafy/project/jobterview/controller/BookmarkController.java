package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.dto.BookmarkDto;
import ssafy.project.jobterview.service.BookmarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    /**
     * 해당 Member가 해당 Room 즐겨찾기 추가
     *
     * @param roomId   Room id
     * @param memberId Member id
     * @return HttpStatus.OK 반환
     */
    @PostMapping("/{roomId}/{memberId}")
    public ResponseEntity<Void> add(@PathVariable(name = "roomId") Long roomId, @PathVariable(name = "memberId") Long memberId) {
        bookmarkService.add(roomId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 해당 Member가 해당 Room 즐겨찾기 취소
     *
     * @param roomId   Room id
     * @param memberId Member id
     * @return HttpStatus.OK 반환
     */
    @DeleteMapping("/{roomId}/{memberId}")
    public ResponseEntity<Void> cancel(@PathVariable(name = "roomId") Long roomId, @PathVariable(name = "memberId") Long memberId) {
        bookmarkService.delete(roomId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 해당 Member의 Bookmark 목록 조회
     *
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<BookmarkDto> 형태로 Bookmark 목록 반환
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<Page<BookmarkDto>> getMemberBookmarks(@PathVariable(name = "memberId") Long memberId, @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(bookmarkService.findByMember(memberId, pageable).map(Bookmark::convertToDto), HttpStatus.OK);
    }
}
