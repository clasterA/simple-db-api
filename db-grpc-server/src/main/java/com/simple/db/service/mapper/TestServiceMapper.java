/* Copyright (C) ...
A.Z.
*/
package com.simple.db.service.mapper;

import com.simple.db.command.CreateTestRecordCommand;
import com.simple.db.command.GetTestRecordCommand;
import com.simple.db.command.UpdateTestRecordCommand;
import com.simple.db.dto.TestDto;
import com.simple.db.proto.Messages;
import com.simple.db.proto.service.GRpcTypeMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring")
public interface TestServiceMapper extends GRpcTypeMapper {

    CreateTestRecordCommand map(Messages.CreateTestRecordCommand request);
    GetTestRecordCommand map(Messages.GetTestRecordCommand request);
    UpdateTestRecordCommand map(Messages.UpdateTestRecordCommand request);

    Messages.TestDto map(TestDto dto);
}
