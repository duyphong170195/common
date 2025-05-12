package com.eight_seneca.common.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private List<T> content;
}
