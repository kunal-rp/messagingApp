package msg.services.testing;

import io.grpc.Context;
import msg.testing.SampleRequest;
import msg.testing.SampleResponse;
import msg.user.UserDetails;
import msg.testing.TestingHandlerGrpc.TestingHandlerImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import utils.*;
import com.google.inject.Inject;
import msg.services.user.UserService;


public class TestingHandlerImpl extends TestingHandlerImplBase {

	private UserService userService;

  @Inject
  TestingHandlerImpl(UserService userService) {
    this.userService=userService;
  }

  public TestingHandlerImpl(){}
	

  @Override
  public void validateJwt(SampleRequest request, StreamObserver<SampleResponse> responseObserver){
    
    responseObserver.onNext(SampleResponse.newBuilder().setUserDetails((UserDetails)Constants.USER_DETAILS_CONTEXT_KEY.get()).build());
    responseObserver.onCompleted();
  }

  @Override
  public void getUser(SampleRequest request, StreamObserver<SampleResponse> responseObserver){
    responseObserver.onNext(SampleResponse.newBuilder().setUser(userService.getUserFromUserDetails((UserDetails)Constants.USER_DETAILS_CONTEXT_KEY.get())).build());
    responseObserver.onCompleted();
  }
}