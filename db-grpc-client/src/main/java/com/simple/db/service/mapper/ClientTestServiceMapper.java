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
public interface ClientTestServiceMapper extends GRpcTypeMapper {
    Messages.CreateTestRecordCommand map(CreateTestRecordCommand command);
    Messages.GetTestRecordCommand map(GetTestRecordCommand command);
    Messages.UpdateTestRecordCommand map(UpdateTestRecordCommand command);

    TestDto map(Messages.TestDto dto);
}
