package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;


public record AuthLoginRequest(@NotBlank String username, @NotBlank String password) {

}
