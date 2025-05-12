package com.eight_seneca.common.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Import({ResponseFactory.class})
public class BaseController {

    @Autowired
    public ResponseFactory responseFactory;

    protected <T> ResponseEntity<BaseResponse<T>> success() {
        return responseFactory.success();
    }

    protected <T> ResponseEntity<BaseResponse<T>> success(T data) {
        return responseFactory.success(data);
    }

    protected <T> ResponseEntity<BaseResponse<T>> fail() {
        return responseFactory.fail();
    }

    protected <T> ResponseEntity<BaseResponse<T>> fail(T data) {
        return responseFactory.fail(data);
    }

}
