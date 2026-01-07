package com.fitnessclub.client_managment_system.GlobalExceptionHendler;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,
        String errorMessage,
        LocalDateTime errorTime
) {

}
