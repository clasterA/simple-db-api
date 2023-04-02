/* Copyright (C) ...
A.Z.
*/
package com.simple.db.grpc;

import com.simple.db.proto.Errors;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@GRpcGlobalInterceptor
@RequiredArgsConstructor
public class GrpcExceptionHandler implements ServerInterceptor {

    private final ErrorMapper errorMapper;

    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata requestHeaders, ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> delegate = next.startCall(call, requestHeaders);
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {
            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (StatusRuntimeException ex) {
                    Metadata metadata = Status.trailersFromThrowable(ex);
                    if (metadata == null || !metadata.containsKey(ProtoUtils.keyForProto(Errors.ErrorResponseModel.getDefaultInstance()))) {
                        metadata = new Metadata();
                        Metadata.Key<Errors.ErrorResponseModel> responseKey = ProtoUtils.keyForProto(Errors.ErrorResponseModel.getDefaultInstance());
                        Errors.ErrorResponseModel errorResponse = handleUnexpectedException(ex);
                        metadata.put(responseKey, errorResponse);
                    }
                    Status status = Status.INTERNAL.withCause(ex);
                    call.close(status, metadata);
                } catch (Exception e) {
                    log.error("Server internal error", e);
                    Status status = Status.INTERNAL.withCause(e);
                    Metadata metadata = new Metadata();
                    Metadata.Key<Errors.ErrorResponseModel> responseKey = ProtoUtils.keyForProto(Errors.ErrorResponseModel.getDefaultInstance());
                    Errors.ErrorResponseModel errorResponse = handleException(e);
                    metadata.put(responseKey, errorResponse);
                    call.close(status, metadata);
                }
            }

            private Errors.ErrorResponseModel handleException(Exception exception) {
                if (exception instanceof NoSuchElementException) {
                    return handleNoSuchElementException((NoSuchElementException) exception);
                }
                return handleUnexpectedException(exception);
            }

            private Errors.ErrorResponseModel handleNoSuchElementException(NoSuchElementException exception) {
                return Errors.ErrorResponseModel.newBuilder()
                                                .setStatus("404")
                                                .setTimestamp(errorMapper.map(LocalDateTime.now()))
                                                .addErrorMessage(Errors.ErrorModel.newBuilder()
                                                                                  .setMessage(exception.getMessage())
                                                                                  .build())
                                                .setCode(Errors.ErrorCode.ERROR_CODE_NOT_FOUND)
                                                .build();
            }

            private Errors.ErrorResponseModel handleUnexpectedException(Exception exception) {
                return Errors.ErrorResponseModel.newBuilder()
                                                .setStatus("500")
                                                .setTimestamp(errorMapper.map(LocalDateTime.now()))
                                                .addErrorMessage(Errors.ErrorModel.newBuilder()
                                                                                  .setMessage(exception.getMessage())
                                                                                  .build())
                                                .setCode(Errors.ErrorCode.ERROR_CODE_UNKNOWN_ERROR)
                                                .build();
            }
        };
    }
}
