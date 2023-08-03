package ssafy.project.jobterview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.project.jobterview.domain.ErrorResult;

@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleAllException(Exception e) {
        ErrorResult errorResult = new ErrorResult(e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
