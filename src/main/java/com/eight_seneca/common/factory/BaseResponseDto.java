package com.eight_seneca.common.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto implements Serializable {

    private Long id;
    private Long createdBy;
    private Long updatedBy;
    private Instant createdAt;
    private Instant updatedAt;

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt.toInstant();
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt.toInstant();
    }
}
