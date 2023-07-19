package ssafy.project.jobterview.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.dto.ReportDto;
import ssafy.project.jobterview.service.ReportService;

@Api(value = "신고 API", tags = {"Report"})
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService rs;

    /**
     * 신고 등록
     * @param report 신고자 닉네임, 피신고자 닉네임, 작성된 신고 사유
     * @return
     */
    @PostMapping
    @ApiOperation(value = "신고 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> register(@RequestBody @ApiParam(value="신고 정보", required = true) ReportDto report) {
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    /**
     * 신고 목록 전체 조회
     * @param pageable 페이징 정보
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "신고 목록 전체 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> searchAll(@PageableDefault(page = 0, size = 10,
            sort = "reportId", direction = Sort.Direction.ASC) @ApiParam(value="페이지 정보", required = true) Pageable pageable) {
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    /**
     * 특정 유저에게 접수된 신고 목록 조회
     * @param pageable 페이징 정보
     * @param nickname 검색 닉네임
     * @return
     */
    @GetMapping
    @ApiOperation(value = "특정 유저에 대한 신고 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> searchById(@PageableDefault(page = 0, size = 10,
            sort = "reportId", direction = Sort.Direction.ASC) @ApiParam(value="페이지 정보", required = true) Pageable pageable,
                                        @RequestParam @ApiParam(value="피신고자 닉네임") String nickname) {
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    /**
     * 신고 삭제
     * @param report
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "신고 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> delete(@RequestParam @ApiParam(value="피신고자, 신고자 정보") ReportDto report) {
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
}
