package com.htv.service;
import com.google.protobuf.Empty;
import com.htv.proto.cv.*;
import io.grpc.ServerServiceDefinition;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class CvServiceImpl extends MutinyCVServiceGrpc.CVServiceImplBase {

    public CvServiceImpl() {
        super();
    }

    @Override
    public MutinyCVServiceGrpc.CVServiceImplBase withCompression(String compression) {
        return super.withCompression(compression);
    }

    @Override
    public Uni<GetCVResponse> getCV(GetCVRequest request) {
        return super.getCV(request);
    }

    @Override
    public Uni<CV> createCV(CreateCVRequest request) {
        return super.createCV(request);
    }

    @Override
    public Uni<CV> updateCV(UpdateCVRequest request) {
        return super.updateCV(request);
    }

    @Override
    public Uni<Empty> deleteCV(DeleteCVRequest request) {
        return super.deleteCV(request);
    }

    @Override
    public Uni<ListCVsResponse> listCVs(ListCVsRequest request) {
        return super.listCVs(request);
    }

    @Override
    public ServerServiceDefinition bindService() {
        return super.bindService();
    }
}