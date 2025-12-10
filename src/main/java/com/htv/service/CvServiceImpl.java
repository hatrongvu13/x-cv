package com.htv.service;
import com.htv.proto.cv.*;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

@GrpcService
public class CvServiceImpl extends CvGrpcServiceGrpc.CvGrpcServiceImplBase {
    @Override
    public void getCV(GetCvGrpcRequest request, StreamObserver<GetCvGrpcResponse> responseObserver) {
        super.getCV(request, responseObserver);
    }

    @Override
    public void createCV(CreateCVRequest request, StreamObserver<CvGrpc> responseObserver) {
        super.createCV(request, responseObserver);
    }

    @Override
    public void updateCV(UpdateCVRequest request, StreamObserver<CvGrpc> responseObserver) {
        super.updateCV(request, responseObserver);
    }

    @Override
    public void deleteCV(DeleteCVRequest request, StreamObserver<CvGrpc> responseObserver) {
        super.deleteCV(request, responseObserver);
    }

    @Override
    public void listCVs(ListCVsRequest request, StreamObserver<ListCvsGrpcResponse> responseObserver) {
        super.listCVs(request, responseObserver);
    }
}