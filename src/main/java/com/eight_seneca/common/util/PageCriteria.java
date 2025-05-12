package com.eight_seneca.common.util;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageCriteria {
    public static final String ASC_SYMBOL = "+";
    public static final String DESC_SYMBOL = "-";
    public static final String DEFAULT_SORT_BY_LAST_MODIFIED_DATE = "updatedAt";

    @Min(value = 0, message = "pageNumber must be positive")
    @Max(value = 1000000, message = "page must be less than 1000000")
    private Integer pageNumber = 0;

    @Min(value = 1, message = "pageSize must be greater than 0")
    @Max(value = 999, message = "pageSize must be less than or equal to 100")
    private Integer pageSize = 50;

    private List<String> sort = new ArrayList<>();
}
