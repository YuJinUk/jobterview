package ssafy.project.jobterview.controller;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.dto.MateDto;
import ssafy.project.jobterview.service.MateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mate")
public class MateController {
    private final MateService mateService;

    // 메이트 신청하기
    @PostMapping
    @ApiOperation(value="메이트 추가")
    @ApiModelProperty(hidden = true)
    public ResponseEntity<?> sendMate(@RequestBody MateDto mateDto){
        mateService.save(mateDto);
        return new ResponseEntity<>(mateDto, HttpStatus.OK);
    }

    // 메이트 삭제하기
    @DeleteMapping
    @ApiOperation(value="메이트 취소")
    @ApiModelProperty(hidden = true)
    public ResponseEntity<?> fromMessageDelete (@RequestBody MateDto mateDto){
        mateService.delete(mateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 메이트 목록 보기
    @GetMapping("/me")
    @ApiOperation(value="메이트 목록")
    public ResponseEntity<Page<MateDto>> findAllByMate (@PageableDefault(page = 0, size = 10) Pageable pageable, @RequestBody String nickname){
        Page<MateDto> mateDtoPage = mateService.findAllByMate(pageable,nickname).map(Mate::convertToDto);
        return new ResponseEntity<>(mateDtoPage, HttpStatus.OK);
    }

}
