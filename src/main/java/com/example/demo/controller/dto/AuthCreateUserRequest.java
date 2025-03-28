package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String name,
                                    @NotBlank String lastName,
                                    @NotBlank String email,
                                    @NotBlank String password
                             ) {
}
