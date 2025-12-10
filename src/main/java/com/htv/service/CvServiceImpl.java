package com.htv.service;
import com.google.protobuf.Empty;
import com.htv.model.entity.CvEntity;
import com.htv.proto.cv.*;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;

@GrpcService
public class CvServiceImpl extends CVServiceGrpc.CVServiceImplBase {

    public CvServiceImpl() {
        super();
    }

    @Blocking
    @Override
    public void getCV(GetCVRequest request, StreamObserver<GetCVResponse> responseObserver) {
        try {
            CvEntity entity = CvEntity.find("FROM CvEntity c WHERE c.id = ?1", request.getCvId())
                    .firstResult();

            if (entity == null) {
                responseObserver.onError(new RuntimeException("Not found"));
                return;
            }

            GetCVResponse response = GetCVResponse.newBuilder().build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void createCV(CreateCVRequest request, StreamObserver<CV> responseObserver) {
        super.createCV(request, responseObserver);
    }

    @Override
    public void updateCV(UpdateCVRequest request, StreamObserver<CV> responseObserver) {
        super.updateCV(request, responseObserver);
    }

    @Override
    public void deleteCV(DeleteCVRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteCV(request, responseObserver);
    }

    @Override
    public void listCVs(ListCVsRequest request, StreamObserver<ListCVsResponse> responseObserver) {
        super.listCVs(request, responseObserver);
    }
}