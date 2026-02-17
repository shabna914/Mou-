package com.example.demo.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponseDto {
    private String message;
    private boolean success;
}
