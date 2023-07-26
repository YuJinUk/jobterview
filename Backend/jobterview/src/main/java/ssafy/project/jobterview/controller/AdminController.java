package ssafy.project.jobterview.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.dto.ChatDto;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.RoomDto;
import ssafy.project.jobterview.service.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberService memberService;
    private final QuestionService questionService;
    private final ReportService reportService;
    private final ChatService chatService;
    private final RoomService roomService;

    @GetMapping("/members")
    @ApiOperation(value="전체 회원 목록")
    public ResponseEntity<Page<MemberDto>> findAllMember (@PageableDefault(page = 0, size = 10,
            sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(memberService.findAll(pageable).map(Member::toMemberDto), HttpStatus.OK);
    }

    @GetMapping("/members/{nickname}")
    @ApiOperation(value = "닉네임으로 회원 검색", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> searchByNickname(@PageableDefault(page = 0, size = 10,
            sort = "nickname", direction = Sort.Direction.ASC) @ApiParam(value="페이지 정보", required = true) Pageable pageable, @RequestParam @ApiParam(value="검색할 닉네임 키워드", required = true) String keyword) {
        Page<MemberDto> members = memberService.findByNicknameContains(pageable, keyword).map(Member::toMemberDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @GetMapping("/members/{email}")
    @ApiOperation(value = "이메일로 회원 검색", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> searchByEmail(@PageableDefault(page = 0, size = 10,
            sort = "nickname", direction = Sort.Direction.ASC) @ApiParam(value="페이지 정보", required = true) Pageable pageable, @RequestParam @ApiParam(value="검색할 닉네임 키워드", required = true) String keyword) {
        Page<MemberDto> members = memberService.findByEmailContains(pageable, keyword).map(Member::toMemberDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @PutMapping("/members")
    @ApiOperation(value = "회원 활성화 / 비활성화")
    public void activeUpdate (@RequestParam String email){
        memberService.update(email);
    }

    /**
     * 특정 keyword를 roomName에 포함하는 Room 목록 조회
     *
     * @param keyword roomName이 포함하는지 조회할 검색어
     * @param pageable 페이징 및 정렬 정보
     * @return ResponseEntity<Page<RoomDto>> 형태로 페이징된 Room 목록 반환
     */
    @GetMapping("/room")
    @ApiOperation(value = "채팅방 검색", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Page<RoomDto>> searchRoomsByKeyword(
            @RequestParam("keyword") String keyword,
            @PageableDefault(page = 0, size = 10,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(roomService.searchByName(keyword, pageable).map(Room::convertToDto), HttpStatus.OK);
    }


    /**
     * 페이징된 Room 목록 조회
     *
     * @param pageable 페이징 및 정렬 정보
     * @return ResponseEntity<Page<RoomDto>> 형태로 페이징된 Room 목록 반환
     */
    @GetMapping("/room/date")
    @ApiOperation(value = "전체 채팅방 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "채팅방 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Page<RoomDto>> getRoomList(
            @PageableDefault(page = 0, size = 10,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        // 페이징된 RoomDto 목록과 HttpStatus.OK 반환
        return new ResponseEntity<>(roomService.findAll(pageable).map(Room::convertToDto), HttpStatus.OK);
    }
    @GetMapping("/chat")
    @ApiOperation(value = "모든 채팅 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Page<ChatDto>> getAllChats(
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findAll(pageable).map(Chat::convertToDto), HttpStatus.OK);
    }

    /**
     * 특정 Member의 채팅 목록 조회
     *
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping("/chat/{memberId}")
    @ApiOperation(value = "특정 맴버 채팅 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Page<ChatDto>> getMemberChats(
            @PathVariable(name = "memberId") Long memberId,
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findByMemberId(memberId,pageable).map(Chat::convertToDto), HttpStatus.OK);
    }

    /**
     * 특정 Room의 채팅 목록 조회
     *
     * @param roomId Room id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping("/chat/{roomId}")
    @ApiOperation(value = "특정 방 채팅 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "채팅 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Page<ChatDto>> getRoomChats(
            @PathVariable(name = "roomId") Long roomId,
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findByRoomId(roomId, pageable).map(Chat::convertToDto), HttpStatus.OK);
    }



}
