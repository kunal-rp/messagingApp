package msg.server;

import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.server.UserHandlerImpl;
import utils.*;

public class UserServer {

  public static void main(String[] args) throws Exception {
  	ServerServiceDefinition intercepted = ServerInterceptors.intercept(new UserHandlerImpl(), new AuthInterceptor());
    BaseServer server = new BaseServer(8081,intercepted,"UserService");
    server.start();
    server.blockUntilShutdown();
  }

}