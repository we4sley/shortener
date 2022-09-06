package com.toyprj.shortener.domain.exception;

public class AlreadyShortenedUrl extends RuntimeException {

    private static String MESSAGE = "이미 단축된 URL 입니다.";

    public AlreadyShortenedUrl() {
        super(MESSAGE);
    }
}
