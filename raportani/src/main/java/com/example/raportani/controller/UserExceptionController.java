package com.example.raportani.controller;

import com.example.raportani.common.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserExceptionController {
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ErrorMessage> exception(GeneralException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
@Getter
@Setter
@AllArgsConstructor
class ErrorMessage{
    private String errorMessage;
}
