/* Copyright (C) ...
A.Z.
*/
package com.simple.db.domain.config;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@TypeDefs({@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
), @TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)})
@MappedSuperclass
public abstract class BaseEntity<T> implements Identifiable<T> {

    @CreatedDate
    @Column(nullable = false)
    protected LocalDateTime createdAt;
}
