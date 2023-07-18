package ssafy.project.jobterview.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.dto.QuestionDto;
import ssafy.project.jobterview.dto.ReportDto;

import java.awt.*;

@Api(value = "면접 질문 API", tags = {"Question"})
@RestController
@RequestMapping("/question")
public class QuestionController {

    @PostMapping
    @ApiOperation(value = "면접 질문 등록", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> register(@RequestBody @ApiParam(value="면접 질문 정보", required = true) QuestionDto quest) {
        Question q = new Question(Category.valueOf(quest.getCategory()), quest.getContent());

        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "면접 질문 수정", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> update(@RequestBody @ApiParam(value="면접 질문 정보", required = true) QuestionDto quest) {
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
}
