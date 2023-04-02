/* Copyright (C) ...
A.Z.
*/
package com.simple.db.service;

import com.simple.db.command.CreateTestRecordCommand;
import com.simple.db.command.GetTestRecordCommand;
import com.simple.db.command.UpdateTestRecordCommand;
import com.simple.db.domain.TestRepository;
import com.simple.db.dto.TestDto;
import com.simple.db.service.mapper.TestServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository repository;
    private final TestServiceMapper mapper;

    @Override
    public TestDto createTestRecord(CreateTestRecordCommand command) {
        var entity = repository.saveAndFlush(mapper.map(command));
        return mapper.map(entity);
    }

    @Override
    public TestDto getTestRecordById(GetTestRecordCommand command) {
        var entity = repository.findById(UUID.fromString(command.getUuid())).orElseThrow();
        return mapper.map(entity);
    }

    @Override
    public TestDto updateTestRecord(UpdateTestRecordCommand command) {
        var entity = repository.findById(UUID.fromString(command.getUuid())).orElseThrow();
        if (command.getName() != null) {
            entity.setName(command.getName());
        }
        if (command.getDescription() != null) {
            entity.setDescription(command.getDescription());
        }
        if (command.getMetadata() != null) {
            entity.setMetadata(command.getMetadata());
        }
        return mapper.map(repository.save(entity));
    }
}
