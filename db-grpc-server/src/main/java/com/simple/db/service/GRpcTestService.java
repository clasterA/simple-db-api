package com.simple.db.service;

import com.simple.db.command.GetTestRecordCommand;
import com.simple.db.proto.Messages;
import com.simple.db.service.mapper.TestServiceMapper;
import com.simple.db.service.proto.TestServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class GRpcTestService extends TestServiceGrpc.TestServiceImplBase {

    private final TestService service;
    private final TestServiceMapper mapper;

    @Override
    public void createTestRecord(Messages.CreateTestRecordCommand request, StreamObserver<Messages.TestDto> responseObserver) {
        var dto = service.createTestRecord(mapper.map(request));
        responseObserver.onNext(mapper.map(dto));
        responseObserver.onCompleted();
    }

    @Override
    public void getTestRecordById(Messages.GetTestRecordCommand request, StreamObserver<Messages.TestDto> responseObserver) {
        var dto = service.getTestRecordById(GetTestRecordCommand.builder().uuid(request.getUuid()).build());
        responseObserver.onNext(mapper.map(dto));
        responseObserver.onCompleted();
    }

    @Override
    public void updateTestRecord(Messages.UpdateTestRecordCommand request, StreamObserver<Messages.TestDto> responseObserver) {
        var dto = service.updateTestRecord(mapper.map(request));
        responseObserver.onNext(mapper.map(dto));
        responseObserver.onCompleted();
    }

}
