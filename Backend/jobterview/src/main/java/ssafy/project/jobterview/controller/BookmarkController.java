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

    @PostMapping("/{roomId}/{memberId}")
    public ResponseEntity<BookmarkDto> add(
            @PathVariable(name = "roomId") Long roomId,
            @PathVariable(name = "memberId") Long memberId) {
        return new ResponseEntity<>(bookmarkService.add(roomId, memberId).convertToDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}/{memberId}")
    public ResponseEntity<Void> cancel(
            @PathVariable(name = "roomId") Long roomId,
            @PathVariable(name = "memberId") Long memberId) {
        bookmarkService.delete(roomId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Page<BookmarkDto>> getMemberBookmarks(
            @PathVariable(name = "memberId") Long memberId,
            @PageableDefault(page = 0, size = 10,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(
                bookmarkService.findByMember(memberId, pageable).map(Bookmark::convertToDto), HttpStatus.OK);
    }
}
