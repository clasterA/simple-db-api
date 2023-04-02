package com.simple.db.command;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateTestRecordCommand {
    @NotBlank
    @Length(min = 2, max = 255)
    private String name;

    @Length(max = 255)
    private String description;

    private String metadata;
}