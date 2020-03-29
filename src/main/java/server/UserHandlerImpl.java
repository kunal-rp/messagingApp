package msg.server;

import msg.userhandler.LoginRequest;
import msg.userhandler.LoginResponse;
import msg.userhandler.UserHandlerGrpc.UserHandlerImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;

/**
   * Our implementation of UserData service.
   *
   * <p>See hello.proto for details of the methods.
   */
  public class UserHandlerImpl extends UserHandlerImplBase {

    public UserHandlerImpl() {}

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver){
      System.out.println("get uesr data called");
      responseObserver.onNext(LoginResponse.newBuilder().setJwt(ByteString.copyFrom("test".getBytes())).build());
      responseObserver.onCompleted();
    }
  }