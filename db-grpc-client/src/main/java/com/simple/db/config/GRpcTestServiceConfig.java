/* Copyright (C) ...
A.Z.
*/
package com.simple.db.config;

import com.simple.db.service.GRpcClientTestServiceImpl;
import com.simple.db.service.TestService;
import com.simple.db.service.mapper.ClientTestServiceMapper;
import com.simple.db.service.proto.TestServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!itest")
@RequiredArgsConstructor
public class GRpcTestServiceConfig {

    @Value("${simpledb.grpc-server.url:localhost}")
    private String gRpcUrl;

    @Value("${simpledb.grpc-server.port:6565}")
    private int gRpcPort;

    private final ClientTestServiceMapper testServiceMapper;

    private Channel channel() {
        return ManagedChannelBuilder
                .forAddress(gRpcUrl, gRpcPort)
                .enableRetry()
                .usePlaintext()
                .build();
    }

    @Bean
    public TestService gRpcClientLedgerTransactionService() {
        return new GRpcClientTestServiceImpl(TestServiceGrpc.newBlockingStub(channel()), testServiceMapper);
    }

}
