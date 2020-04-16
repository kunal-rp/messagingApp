package msg.server;

import io.grpc.Context;
import msg.userhandler.LoginRequest;
import msg.userhandler.LoginResponse;
import msg.userhandler.UserHandlerGrpc.UserHandlerImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import utils.*;

/**
   * Our implementation of UserData service.
   *
   * <p>See hello.proto for details of the methods.
   */
  public class UserHandlerImpl extends UserHandlerImplBase {

    public UserHandlerImpl() {}

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver){
        responseObserver.onNext(LoginResponse.getDefaultInstance());
    }
  }