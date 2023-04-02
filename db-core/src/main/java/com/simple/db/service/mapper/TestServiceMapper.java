package com.simple.db.service.mapper;

import com.simple.db.command.CreateTestRecordCommand;
import com.simple.db.domain.TestEntity;
import com.simple.db.dto.TestDto;
import org.mapstruct.*;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface TestServiceMapper {

    TestEntity map(CreateTestRecordCommand command);

    @Mapping(target = "uuid", source = "source.id")
    TestDto map(TestEntity source);

}
