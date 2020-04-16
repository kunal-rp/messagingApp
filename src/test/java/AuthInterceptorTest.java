package msg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import io.grpc.Metadata;
import com.google.protobuf.ByteString;
import io.grpc.ServerCall;
import io.grpc.Context;
import io.grpc.stub.ServerCalls;
import io.grpc.ServerCallHandler;
import io.grpc.MethodDescriptor;
import utils.Constants;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import utils.JwtUtil;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.server.testing.TestingHandlerImpl;
import msg.testing.SampleRequest;
import msg.testing.SampleResponse;
import msg.userhandler.UserDetails;
import utils.*;
import utils.AuthInterceptor;
import java.io.InputStream;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import msg.testing.TestingHandlerGrpc;
import io.grpc.Server;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;

@RunWith(JUnit4.class)
public class AuthInterceptorTest {

 @Test
 public void shouldFailWithStatusUnauthenticated() throws Exception {


   StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> {
     TestingHandlerGrpc.TestingHandlerBlockingStub blockingStub = setupServer();
     SampleResponse response = blockingStub.validateJwt(SampleRequest.getDefaultInstance());
   }, "Expected Status runtime exception");
 }


 @Test
 public void shouldSucsessfullyAuthenticate() throws Exception {

  TestingHandlerGrpc.TestingHandlerBlockingStub blockingStub = setupServer();

  UserDetails testUserDetails = UserDetails.newBuilder().setUserId(1000).build();

  Metadata headers = new Metadata();
  headers.put(Constants.AUTHORIZATION_METADATA_KEY, JwtUtil.createJwt(testUserDetails));
  blockingStub = MetadataUtils.attachHeaders(blockingStub, headers);
  SampleResponse response = blockingStub.validateJwt(SampleRequest.getDefaultInstance());

  assertEquals(response.getUserDetails(), testUserDetails);
}

private TestingHandlerGrpc.TestingHandlerBlockingStub setupServer() throws Exception{
  ServerServiceDefinition intercepted = ServerInterceptors.intercept(new TestingHandlerImpl(), new AuthInterceptor());
  String uniqueName = InProcessServerBuilder.generateName();
  Server server = InProcessServerBuilder.forName(uniqueName).directExecutor()
  .addService(intercepted)
  .build()
  .start();
  ManagedChannel channel = InProcessChannelBuilder.forName(uniqueName)
  .directExecutor()
  .build();
  return TestingHandlerGrpc.newBlockingStub(channel);

}

}