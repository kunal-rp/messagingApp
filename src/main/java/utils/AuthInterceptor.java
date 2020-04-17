package utils;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import java.util.logging.Logger;
import io.grpc.Status;
import com.google.protobuf.InvalidProtocolBufferException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import msg.user.UserDetails;
import com.google.protobuf.ByteString;
import java.util.Base64;


/** Interceptor that validates user's identity. */
public class AuthInterceptor implements ServerInterceptor {


  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
    ServerCall<ReqT, RespT> call,
    Metadata metadata,
    ServerCallHandler<ReqT, RespT> next) {

    Status status = Status.UNAUTHENTICATED;
    try {

      Jws<Claims> claims = JwtUtil.parseJwt(metadata);
      UserDetails userDetails = UserDetails.parseFrom(Base64.getDecoder().decode((String)claims.getBody().get("userDetails")));
      Context ctx = Context.current().withValue(Constants.USER_DETAILS_CONTEXT_KEY, userDetails);
      return Contexts.interceptCall(ctx, call, metadata, next);
    }
    catch(Exception e){
      status = Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);
    }
    call.close(status, metadata);
    return new ServerCall.Listener() {};
  }

}