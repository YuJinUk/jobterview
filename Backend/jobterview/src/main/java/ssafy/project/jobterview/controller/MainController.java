package ssafy.project.jobterview.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.dto.RoomDto;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("welcome Test BackEnd", HttpStatus.OK);
    }
    @GetMapping("/health_check")
    public ResponseEntity<String> health_check(){
        return new ResponseEntity<>("health_check", HttpStatus.OK);
    }
}
