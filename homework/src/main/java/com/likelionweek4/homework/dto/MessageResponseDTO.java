package com.likelionweek4.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MessageResponseDTO {

    @Getter
    @AllArgsConstructor
    public static class Message {
        private String message;
    }
}
