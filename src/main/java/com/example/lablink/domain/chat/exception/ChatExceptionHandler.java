package com.example.lablink.domain.chat.exception;

import com.example.lablink.global.message.ResponseMessage;
import com.example.lablink.domain.study.exception.StudyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ChatExceptionHandler {
    @ExceptionHandler(value = { StudyException.class })
    protected ResponseEntity<ResponseMessage> handleCustomException(StudyException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ResponseMessage.ErrorResponse(e.getErrorCode());
    }
}