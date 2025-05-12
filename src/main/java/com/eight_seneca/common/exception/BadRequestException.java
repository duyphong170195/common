package com.eight_seneca.common.exception;

import com.eight_seneca.common.internationalization.Translator;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private String code;
    private Object[] params;

    public BadRequestException(String code) {
        super(Translator.getMessage(code));
        this.code = code;
    }

    public BadRequestException(String code, Object... params) {
        super(Translator.getMessage(code, params));
        this.code = code;
        this.params = params;
    }

}
