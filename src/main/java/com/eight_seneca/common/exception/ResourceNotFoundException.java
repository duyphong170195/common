package com.eight_seneca.common.exception;

import com.eight_seneca.common.internationalization.Translator;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String code;
    private Object[] params;

    public ResourceNotFoundException(String code) {
        super(Translator.getMessage(code));
        this.code = code;
    }

    public ResourceNotFoundException(String code, Object... params) {
        super(Translator.getMessage(code, params));
        this.code = code;
        this.params = params;
    }

}
