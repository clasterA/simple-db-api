package com.simple.db.command;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class GetTestRecordCommand {
    @NotBlank
    private String uuid;
}