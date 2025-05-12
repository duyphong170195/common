package com.eight_seneca.common.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubError {

    private String object;
    private String field;
    private Object value;
    private String message;

}
