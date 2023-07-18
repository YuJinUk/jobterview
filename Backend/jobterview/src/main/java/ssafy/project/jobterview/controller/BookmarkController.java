package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.auth.JobterviewMemberDetails;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.service.BookmarkService;
import ssafy.project.jobterview.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final RoomService roomService;

    @PostMapping("/{roomId}")
    public ResponseEntity<String> add( @PathVariable Long roomId) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
//        JobterviewMemberDetails memberDetails = (JobterviewMemberDetails) authentication.getDetails();
//        String memberEmail = memberDetails.getUsername();
//        Member member = memberService.getMemberByEmail(email);

        //임시로 사용할 맴버 변수
        return null;
    }
}
