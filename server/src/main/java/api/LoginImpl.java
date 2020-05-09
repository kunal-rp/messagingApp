package msg.api.login;

import io.grpc.Context;
import msg.api.login.LoginRequest;
import msg.api.login.LoginResponse;
import msg.api.login.LoginGrpc.LoginImplBase;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import utils.*;
import msg.services.database.DatabaseService;
import msg.services.auth.SSOAuthService;
import msg.auth.AuthenticationResult;
import com.google.inject.Inject;

public class LoginImpl extends LoginImplBase {

 private DatabaseService databaseService;
 private SSOAuthService authService;

 public LoginImpl() {}

 @Inject
 public void setService(DatabaseService databaseService, SSOAuthService authService){
  this.databaseService = databaseService;
  this.authService=authService;
}

@Override
public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver){
  AuthenticationResult authResult;
  if(request.hasGoogleToken()){

    authResult = authService.getGoogleUserInfo(request.getGoogleToken());

    if(authResult.getStatus() == AuthenticationResult.Status.FAIL){
      failedLogin(responseObserver); 
    }

    databaseService.createUserIfNeeded(authResult.getUser());

    responseObserver.onNext(LoginResponse.newBuilder().setLoginResult(LoginResponse.Result.SUCSESS ).setJwtToken(JwtUtil.createJwt(authResult.getUser())).build());
    responseObserver.onCompleted();
  }
  else{
    failedLogin(responseObserver); 
 }
}

private void failedLogin(StreamObserver<LoginResponse> responseObserver){
  responseObserver.onNext(LoginResponse.newBuilder().setLoginResult(LoginResponse.Result.FAIL).build());
   responseObserver.onCompleted(); 
}

}