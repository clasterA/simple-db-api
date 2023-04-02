package com.simple.db.domain;

import com.simple.db.EntitySchema;
import com.simple.db.domain.config.BaseUUIDEntity;
import com.simple.db.enums.RecordStateEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "test", schema = EntitySchema.NAME)
public class TestEntity extends BaseUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordStateEnum state = RecordStateEnum.ACTIVE;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String metadata;

}
