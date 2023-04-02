/* Copyright (C) ...
A.Z.
*/
package com.simple.db.proto.service;

import com.google.protobuf.Timestamp;
import com.google.type.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Mapper
public interface GRpcTypeMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    int MONEY_SCALE = 9;

    default Timestamp map(LocalDateTime from) {
        final Instant instant = java.sql.Timestamp.valueOf(from).toInstant();
        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

    default LocalDateTime map(Timestamp from) {
        final Instant instant = Instant.ofEpochSecond(from.getSeconds(), from.getNanos());
        return LocalDateTime.from(instant.atZone(ZoneId.systemDefault()));
    }

    default BigDecimal map(Money from) {
        return BigDecimal.valueOf(from.getUnits())
                         .add(BigDecimal.valueOf(from.getNanos(), MONEY_SCALE));
    }

    default Money map(BigDecimal from) {
        return Money.newBuilder()
                    .setUnits(from.toBigInteger().longValue())
                    .setNanos(from.remainder(BigDecimal.ONE).movePointRight(MONEY_SCALE).intValue())
                    .build();
    }

    default Timestamp mapDate(LocalDate from) {
        Instant instant = java.sql.Timestamp.valueOf(LocalDateTime.of(from, LocalTime.MIDNIGHT)).toInstant();
        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

    default LocalDate mapDate(Timestamp from) {
        Instant instant = Instant.ofEpochSecond(from.getSeconds(), from.getNanos());
        return LocalDate.from(instant.atZone(ZoneId.systemDefault()));
    }

    default ZonedDateTime mapZonedDateTime(Timestamp from) {
        final Instant instant = Instant.ofEpochSecond(from.getSeconds(), from.getNanos());
        return ZonedDateTime.from(instant.atZone(ZoneId.systemDefault()));
    }

    default Timestamp mapZonedDateTime(ZonedDateTime from) {
        final Instant instant = from.toInstant();
        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

    default String map(ZonedDateTime from) {
        return DateTimeFormatter.ISO_DATE_TIME.format(from);
    }

    default ZonedDateTime mapZonedDateTimeFromString(String from) {
        try {
            return ZonedDateTime.from(DATE_TIME_FORMATTER.parse(from));
        } catch (DateTimeParseException e) {
            return ZonedDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(from));
        }
    }
}
