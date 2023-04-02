/* Copyright (C) ...
A.Z.
*/
package com.simple.db.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseUUIDEntity extends BaseEntity<UUID> {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @Parameter(
                    name = "pg-uuid_gen",
                    value = "com.simple.db.domain.config.PGUUIDStrategy"
            )
    )
    protected UUID id;
}
