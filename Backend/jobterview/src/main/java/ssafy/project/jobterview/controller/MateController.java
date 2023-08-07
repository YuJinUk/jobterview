package ssafy.project.jobterview.controller;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MateDto;
import ssafy.project.jobterview.service.MateService;
import ssafy.project.jobterview.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mate")
public class MateController {

    private final MateService mateService;
    private final MemberService memberService;

    // 메이트 신청하기
    @PostMapping
    @ApiOperation(value = "메이트 추가")
    @ApiModelProperty(hidden = true)
    public ResponseEntity<?> makeMate(@RequestParam String fromNickname,
                                      @RequestParam String toNickname) {
        MateDto savedMateDto = mateService.save(fromNickname, toNickname);
        return new ResponseEntity<>(savedMateDto, HttpStatus.OK);
    }

    // 메이트 삭제하기
    @DeleteMapping
    @ApiOperation(value = "메이트 취소")
    @ApiModelProperty(hidden = true)
    public ResponseEntity<?> breakMate(@RequestParam String fromNickname,
                                       @RequestParam String toNickname) {
        mateService.delete(fromNickname, toNickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 메이트 목록 보기
    @GetMapping("/me")
    @ApiOperation(value = "메이트 목록")
    public ResponseEntity<Page<MateDto>> findAllByMate(
            @PageableDefault(page = 0, size = 50, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String nickname) {

        System.out.println(nickname);

        Page<MateDto> mateDtoPage = mateService.findAllByMate(pageable, nickname).map(Mate::convertToDto);


        System.out.println("size : " + mateDtoPage.getContent().size());
        return new ResponseEntity<>(mateDtoPage, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation(value = "해당 맴버가 등록한 메이트 목록 조회")
    public ResponseEntity<List<MateDto>> findAllByFromMember(@RequestParam String nickname) {
        Member findMember = memberService.findByNickname(nickname);

        List<MateDto> mateDtoList = mateService.findByFromMember(findMember)
                .stream()
                .map(Mate::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(mateDtoList, HttpStatus.OK);
    }
}
