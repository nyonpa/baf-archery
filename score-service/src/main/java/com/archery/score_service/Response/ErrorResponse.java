package com.archery.score_service.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private long timestamp = System.currentTimeMillis();

    public ErrorResponse(String message) {
        this.message = message;
    }

    // getters
}