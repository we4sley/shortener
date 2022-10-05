package com.toyprj.shortener.presentation.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private HttpStatus httpStatus;
    private String url;

    public static ApiResponse success(HttpStatus httpStatus, String url) {
        return new ApiResponse(httpStatus, url);
    }
}
