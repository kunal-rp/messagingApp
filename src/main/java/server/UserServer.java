package msg.server;

import msg.server.UserHandlerImpl;
import utils.BaseServer;

public class UserServer {

  public static void main(String[] args) throws Exception {
    BaseServer server = new BaseServer(8081,new UserHandlerImpl(),"UserService");
    server.start();
    server.blockUntilShutdown();
  }

}