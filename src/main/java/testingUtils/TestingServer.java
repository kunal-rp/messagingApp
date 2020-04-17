package msg.services.testing;

import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.services.testing.TestingHandlerImpl;
import utils.*;

public class TestingServer {

  public static void main(String[] args) throws Exception {
  	ServerServiceDefinition intercepted = ServerInterceptors.intercept(new TestingHandlerImpl(), new AuthInterceptor());
    BaseServer server = new BaseServer(8082,intercepted,"TestingService");
    server.start();
    server.blockUntilShutdown();
  }

}