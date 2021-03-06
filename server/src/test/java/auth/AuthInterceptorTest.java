package msg.test.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;
import org.junit.Rule;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import io.grpc.Metadata;
import io.grpc.Context;
import utils.Constants;
import io.grpc.Status;
import utils.JwtUtil;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.services.testing.TestingHandlerImpl;
import msg.testing.SampleRequest;
import msg.testing.SampleResponse;
import msg.user.UserDetails;
import utils.AuthInterceptor;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import msg.testing.TestingHandlerGrpc;
import io.grpc.Server;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;


@RunWith(JUnit4.class)
public class AuthInterceptorTest {


  @Test
  public void shouldSucsessfullyAuthenticate() throws Exception {

    TestingHandlerGrpc.TestingHandlerBlockingStub blockingStub = setupServer();
    UserDetails testUserDetails = UserDetails.newBuilder().setUserId("1000").build();
    Metadata headers = new Metadata();
    headers.put(Constants.AUTHORIZATION_METADATA_KEY, JwtUtil.createJwt(testUserDetails));
    blockingStub = MetadataUtils.attachHeaders(blockingStub, headers);
    SampleResponse response = blockingStub.validateJwt(SampleRequest.getDefaultInstance());

    assertEquals(response.getUserDetails(), testUserDetails);
  }
  
  @Test
  public void shouldFailWithStatusUnauthenticated() throws Exception {

   StatusRuntimeException exception = assertThrows(StatusRuntimeException.class, () -> {
     TestingHandlerGrpc.TestingHandlerBlockingStub blockingStub = setupServer();
     SampleResponse response = blockingStub.validateJwt(SampleRequest.getDefaultInstance());
     //no jwt set in metadata
   }, "Expected Status runtime exception");
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