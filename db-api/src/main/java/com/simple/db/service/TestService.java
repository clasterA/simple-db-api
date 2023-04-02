/* Copyright (C) ...
A.Z.
*/
package com.simple.db.service;

import com.simple.db.command.CreateTestRecordCommand;
import com.simple.db.command.GetTestRecordCommand;
import com.simple.db.command.UpdateTestRecordCommand;
import com.simple.db.dto.TestDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface TestService {

    TestDto createTestRecord(@Valid CreateTestRecordCommand command);

    TestDto getTestRecordById(@Valid GetTestRecordCommand command);

    TestDto updateTestRecord(@Valid UpdateTestRecordCommand command);
}
