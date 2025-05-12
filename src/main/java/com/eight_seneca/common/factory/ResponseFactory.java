package com.eight_seneca.common.factory;

import com.eight_seneca.common.exception.BaseMessageCode;
import com.eight_seneca.common.internationalization.Translator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ResponseFactory {

    public <T> ResponseEntity<BaseResponse<T>> success() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(getStatus(BaseMessageCode.SUCCESS));
        baseResponse.getStatus().setSuccess(true);
        return ResponseEntity.ok(baseResponse);
    }

    public <T> ResponseEntity<BaseResponse<T>> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setStatus(getStatus(BaseMessageCode.SUCCESS));
        baseResponse.getStatus().setSuccess(true);
        return ResponseEntity.ok(baseResponse);
    }

    public <T> ResponseEntity<BaseResponse<T>> fail() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(getStatus(BaseMessageCode.ERROR));
        return ResponseEntity.internalServerError().body(baseResponse);
    }

    public <T> ResponseEntity<BaseResponse<T>> fail(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setStatus(getStatus(BaseMessageCode.ERROR));
        return ResponseEntity.internalServerError().body(baseResponse);
    }

    public <T> ResponseEntity<BaseResponse<T>> fail(String code) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(getStatus(code));
        return ResponseEntity.internalServerError().body(baseResponse);
    }

    public <T> ResponseEntity<BaseResponse<T>> fail(T data, String code) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setStatus(getStatus(code));
        return ResponseEntity.internalServerError().body(baseResponse);
    }

    private BaseResponseStatus getStatus(String code) {
        return new BaseResponseStatus(code, Translator.getMessage(code));
    }
}

