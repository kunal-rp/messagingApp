package msg.api.sample;

import io.grpc.Context;
import msg.user.UserDetails;
import msg.api.sample.SampleRequest;
import msg.api.sample.SampleResponse;
import msg.api.sample.SampleGrpc.SampleImplBase;
import io.grpc.stub.StreamObserver;
import utils.Constants;

public class SampleImpl extends SampleImplBase {

	@Override
	public void sampleCall(SampleRequest request, StreamObserver<SampleResponse> responseObserver){
		System.out.println("sample call");
		System.out.println((UserDetails)Constants.USER_DETAILS_CONTEXT_KEY.get());
		responseObserver.onNext(SampleResponse.newBuilder().setUserDetails((UserDetails) Constants.USER_DETAILS_CONTEXT_KEY.get()).build());
		responseObserver.onCompleted();
	}

}