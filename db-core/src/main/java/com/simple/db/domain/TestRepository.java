/* Copyright (C) ...
A.Z.
*/
package com.simple.db.domain;

import com.simple.db.enums.RecordStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TestRepository extends JpaRepository<TestEntity, UUID> {
    List<TestEntity> findAllByIdInAndState(List<UUID> code, RecordStateEnum state);
}
