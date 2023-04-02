/* Copyright (C) ...
A.Z.
*/
package com.simple.db.service;

import com.simple.db.command.CreateTestRecordCommand;
import com.simple.db.command.GetTestRecordCommand;
import com.simple.db.command.UpdateTestRecordCommand;
import com.simple.db.dto.TestDto;
import com.simple.db.service.mapper.ClientTestServiceMapper;
import com.simple.db.service.proto.TestServiceGrpc;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GRpcClientTestServiceImpl implements TestService {

    private final TestServiceGrpc.TestServiceBlockingStub clientStub;
    private final ClientTestServiceMapper mapper;

    @Override
    public TestDto createTestRecord(CreateTestRecordCommand command) {
        var dto = clientStub.createTestRecord(mapper.map(command));
        return mapper.map(dto);
    }

    @Override
    public TestDto getTestRecordById(GetTestRecordCommand command) {
        return mapper.map(clientStub.getTestRecordById(mapper.map(command)));
    }

    @Override
    public TestDto updateTestRecord(UpdateTestRecordCommand command) {
        var dto = clientStub.updateTestRecord(mapper.map(command));
        return mapper.map(dto);
    }
}
