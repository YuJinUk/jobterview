package ssafy.project.jobterview.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.dto.QuestionDto;
import ssafy.project.jobterview.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "면접 질문 API", tags = {"Question"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 면접 질문 등록
     *
     * @param quest : Front에서 작성된 질문 Dto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "면접 질문 등록", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> register(@RequestBody @ApiParam(value = "면접 질문 정보", required = true) QuestionDto quest) {
        Question q = new Question(Category.valueOf(quest.getCategory()), quest.getContent());
        questionService.save(q);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    /**
     * 면접 질문 수정
     *
     * @param quest : Front에서 수정된 질문 Dto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "면접 질문 수정", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> update(@RequestBody @ApiParam(value = "면접 질문 정보", required = true) QuestionDto quest) {
        Question q = new Question(quest.getQuestionId(), Category.valueOf(quest.getCategory()), quest.getContent());
        questionService.update(q);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    /**
     * 면접 질문 목록 전체 조회
     *
     * @param pageable 페이징 정보
     * @return
     */
    @GetMapping("/list/paging")
    @ApiOperation(value = "면접 질문 목록 조회 (페이징)", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> selectAllWithPaging(
            @PageableDefault(page = 0, size = 10, sort = "selectedCnt", direction = Sort.Direction.DESC)
            @ApiParam(value = "페이지 정보", required = true) Pageable pageable) {
        Page<QuestionDto> questionDtoList = questionService.findAllWithPaging(pageable).map(Question::toQuestionDto);
        return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
    }

    /**
     * 면접 질문 목록 전체 조회
     *
     * @return
     */
    @GetMapping("/list")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> selectAll() {
        List<QuestionDto> questionDtoList = questionService.findAll().stream()
                .map(Question::toQuestionDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
    }

    /**
     * 면접 질문 분류 목록 조회
     *
     * @param pageable 페이징 정보
     * @param category 조회 할 질문 분류
     * @return
     */
    @GetMapping
    @ApiOperation(value = "면접 질문 분류 별 목록 조회 (페이징)", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> selectAllByCategory(
            @PageableDefault(page = 0, size = 10, sort = "category", direction = Sort.Direction.DESC)
            @ApiParam(value = "페이지 정보") Pageable pageable,
            @ApiParam(value = "분류 정보", required = true)
            @RequestParam String category) {
        System.out.println("들어왔니?");

        Page<QuestionDto> questionDtoList = questionService.findAllByCategory(Category.valueOf(category), pageable).map(Question::toQuestionDto);
        return new ResponseEntity<>(questionDtoList, HttpStatus.OK);
    }

    /**
     * 질문 삭제
     *
     * @param quest 삭제할 질문 Dto
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "면접 질문 삭제", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> delete(@RequestBody @ApiParam(value = "면접 질문 정보", required = true) QuestionDto quest) {
        questionService.deleteById(quest.getQuestionId());
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    /**
     * 랜덤 질문 선택
     *
     * @param count 랜덤으로 선택할 질문 개수
     * @return
     */
    @GetMapping("/random")
    @ApiOperation(value = "면접 질문 랜덤 선택", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> selectByRandom(@RequestParam @ApiParam(value = "랜덤 선택 개수", required = true) int count) {
        List<QuestionDto> questionDtoList = questionService.findByRandom(count)
                .stream()
                .map(Question::toQuestionDto)
                .collect(Collectors.toList());

        return new ResponseEntity<List<QuestionDto>>(questionDtoList, HttpStatus.OK);
    }
}
