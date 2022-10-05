package com.toyprj.shortener.domain.exception;

public class NoSuchUrlExist extends RuntimeException {

    private static String MESSAGE = "해당 URL이 존재하지 않습니다.";

    public NoSuchUrlExist() {
        super(MESSAGE);
    }
}
