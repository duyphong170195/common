package com.eight_seneca.common.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseStatus {

    private boolean success;
    private String code;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<SubError> subErrors;

    public BaseResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
