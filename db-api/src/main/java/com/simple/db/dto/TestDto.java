package com.simple.db.dto;

import com.simple.db.enums.RecordStateEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestDto {

    private String uuid;

    private String name;

    private String description;

    private RecordStateEnum state;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    private String metadata;

}
