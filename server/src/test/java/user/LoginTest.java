package msg.test.user;

import static org.junit.Assert.assertEquals;

import java.util.*;
import org.junit.Rule;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.concurrent.TimeUnit;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.api.login.LoginImpl;
import msg.api.login.LoginRequest;
import msg.api.login.LoginResponse;
import msg.user.User;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import msg.api.login.LoginGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import com.google.inject.Guice;
import com.google.inject.Injector;
import msg.services.auth.SSOAuthService;
import msg.services.auth.FakeSSOAuthService;
import msg.services.database.DatabaseService;
import msg.services.database.FakeDatabaseService;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import msg.auth.AuthenticationResult;


@RunWith(JUnit4.class)
public class LoginTest {

  private Injector injector;
  private Server server;
  private LoginGrpc.LoginBlockingStub blockingStub;
  private ManagedChannel channel;

  private FakeSSOAuthService fakeAuthService = new FakeSSOAuthService();
  private FakeDatabaseService fakeDatabaseService = new FakeDatabaseService();
  
  @Before
  public void setUp() throws Exception {
    injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        bind(SSOAuthService.class).toInstance(fakeAuthService);
        bind(DatabaseService.class).toInstance(fakeDatabaseService);
      }
    });
  }

  @Before
  public void startServer() throws Exception{
    blockingStub = setupServer();
  }

  @After
  public void tearDown() throws Exception {
    injector = null;
    fakeAuthService.deleteAll();
  }

  @After
  public void stopServer() throws InterruptedException{
    server.shutdownNow().awaitTermination();
    channel.shutdownNow().awaitTermination(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldPassLogin_existingUser() throws Exception {

    fakeAuthService.setAuthResult(AuthenticationResult.newBuilder().setStatus(AuthenticationResult.Status.SUCCESS).build());
    
    LoginResponse response = blockingStub.login(LoginRequest.newBuilder().setGoogleToken("sample google access token").build());

    assertEquals(response.getLoginResult(), LoginResponse.Result.SUCSESS );
  }


  @Test
  public void shouldPassLogin_createNewUser() throws Exception {

    User sampleUserFromGoogle = User.newBuilder().setUserId("2002").setEmail("intest@gmail.com").build();
    fakeAuthService.setAuthResult(AuthenticationResult.newBuilder().setStatus(AuthenticationResult.Status.SUCCESS).setUser(sampleUserFromGoogle).build());
    LoginResponse response = blockingStub.login(LoginRequest.newBuilder().setGoogleToken("sample google access token").build());

    assertEquals(response.getLoginResult(), LoginResponse.Result.SUCSESS);
    assertEquals(sampleUserFromGoogle, fakeDatabaseService.getCreatedUser().get());
  }

  @Test
  public void shouldFailLoginFromSSOAuth() throws Exception {
    LoginResponse response = blockingStub.login(LoginRequest.newBuilder().setGoogleToken("sample google access token").build());

    assertEquals(response.getLoginResult(), LoginResponse.Result.FAIL );
  }
  
  private LoginGrpc.LoginBlockingStub setupServer() throws Exception{
    String uniqueName = InProcessServerBuilder.generateName();
    server = InProcessServerBuilder.forName(uniqueName).directExecutor()
    .addService(injector.getInstance(LoginImpl.class))
    .build()
    .start();
    channel = InProcessChannelBuilder.forName(uniqueName)
    .directExecutor()
    .build();
    return LoginGrpc.newBlockingStub(channel);

  }

}