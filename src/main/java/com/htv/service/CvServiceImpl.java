package com.htv.service;
import com.htv.proto.cv.*;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class CvServiceImpl extends MutinyCvGrpcServiceGrpc.CvGrpcServiceImplBase {

    @Override
    public Uni<GetCvGrpcResponse> getCV(GetCvGrpcRequest request) {
        System.out.println("getCV");
        return Uni.createFrom().item(GetCvGrpcResponse.newBuilder().build());
    }

    @Override
    public Uni<CvGrpc> createCV(CreateCVRequest request) {
        return Uni.createFrom().item(CvGrpc.newBuilder().build());
    }

    @Override
    public Uni<CvGrpc> updateCV(UpdateCVRequest request) {
        return super.updateCV(request);
    }

    @Override
    public Uni<CvGrpc> deleteCV(DeleteCVRequest request) {
        return super.deleteCV(request);
    }

    @Override
    public Uni<ListCvsGrpcResponse> listCVs(ListCVsRequest request) {
        return super.listCVs(request);
    }
}