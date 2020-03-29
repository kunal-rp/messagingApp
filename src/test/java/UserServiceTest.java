package msg.test;

import static org.junit.Assert.assertEquals;

import msg.server.UserHandlerImpl;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.google.protobuf.ByteString;
import msg.userhandler.UserHandlerGrpc;

import msg.userhandler.LoginRequest;
import msg.userhandler.LoginResponse;


@RunWith(JUnit4.class)
public class UserServiceTest {

  @Rule
  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();


  @Test
  public void shouldLogin() throws Exception {
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(new UserHandlerImpl()).build().start());

    UserHandlerGrpc.UserHandlerBlockingStub blockingStub = UserHandlerGrpc.newBlockingStub(
        // Create a client channel and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

    LoginResponse response =
        blockingStub.login(LoginRequest.getDefaultInstance());

    assertEquals(response.getJwt(), ByteString.copyFrom("test".getBytes()));
  }
}