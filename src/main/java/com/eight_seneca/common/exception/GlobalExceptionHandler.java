package com.eight_seneca.common.exception;


import com.eight_seneca.common.factory.BaseResponse;
import com.eight_seneca.common.factory.BaseResponseStatus;
import com.eight_seneca.common.factory.SubError;
import com.eight_seneca.common.internationalization.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return createResponse(BaseMessageCode.ERROR, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponse<BaseResponseStatus>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        BaseResponse<BaseResponseStatus> baseResponse = new BaseResponse<>();
        BaseResponseStatus status = new BaseResponseStatus();
        status.setCode(HttpStatus.BAD_REQUEST.name());
        status.setMessage(Translator.getMessage(BaseMessageCode.VALIDATION_ERROR));

        List<SubError> subErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            subErrors.add(new SubError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
        }
        status.setSubErrors(subErrors);

        baseResponse.setStatus(status);
        return ResponseEntity.badRequest().body(baseResponse);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<BaseResponse<BaseResponseStatus>> handleCustomException(CustomException ex) {
        log.error(ex.getMessage(), ex);
        BaseResponse<BaseResponseStatus> responseObject = new BaseResponse<>();
        responseObject.setStatus(new BaseResponseStatus(ex.getCode(), ex.getMessage()));
        return ResponseEntity.status(ex.getHttpStatus()).body(responseObject);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<BaseResponse<Object>> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return createResponse(ex.getCode(), ex.getMessage(), ex.getParams());
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<BaseResponse<BaseResponseStatus>> handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage(), ex);
        BaseResponse<BaseResponseStatus> responseObject = new BaseResponse<>();
        responseObject.setStatus(new BaseResponseStatus(ex.getCode(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<BaseResponse<BaseResponseStatus>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        BaseResponse<BaseResponseStatus> responseObject = new BaseResponse<>();
        responseObject.setStatus(new BaseResponseStatus(ex.getCode(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
    }

    private ResponseEntity<BaseResponse<Object>> createResponse(String code, String message, Object... params) {
        BaseResponse<Object> responseObject = new BaseResponse<>();
        responseObject.setStatus(new BaseResponseStatus(code, message));
        responseObject.setData(params);
        return ResponseEntity.internalServerError().body(responseObject);
    }
}


