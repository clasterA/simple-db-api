/* Copyright (C) ...
A.Z.
*/
package com.simple.db.grpc;

import com.simple.db.proto.service.GRpcTypeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper extends GRpcTypeMapper {
}
