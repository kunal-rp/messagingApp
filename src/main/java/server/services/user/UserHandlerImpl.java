package msg.services.user;

import io.grpc.Context;
import msg.user.UserDetails;
import msg.user.User;
import msg.userhandler.LoginRequest;
import msg.userhandler.LoginResponse;
import msg.userhandler.UserHandlerGrpc.UserHandlerImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import utils.*;

public class UserHandlerImpl extends UserHandlerImplBase {


  public UserHandlerImpl() {}

  @Override
  public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver){
    responseObserver.onNext(LoginResponse.getDefaultInstance());
    responseObserver.onCompleted();
  }

  @Override
  public void getUserFromDetails(UserDetails userDetails, StreamObserver<User> responseObserver){
  	responseObserver.onNext(User.newBuilder().setUserId(userDetails.getUserId()).setEmail("From impl").build());
  	responseObserver.onCompleted();
  }

}