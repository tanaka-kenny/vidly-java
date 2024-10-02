package com.spring.vidly.dto;

public record LoginResponse (String token, long expiresAt) {
}
