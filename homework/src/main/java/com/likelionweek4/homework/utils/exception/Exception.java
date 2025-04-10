package com.likelionweek4.homework.exception;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.restaurant.RestaurantResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponseDTO.Message> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponseDTO.Message(e.getMessage()));
    }
}
