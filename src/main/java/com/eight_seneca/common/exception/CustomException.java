package com.eight_seneca.common.exception;

import com.eight_seneca.common.internationalization.Translator;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;

    public CustomException(HttpStatus httpStatus, String code, Object... params) {
        super(Translator.getMessage(code, params));
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
