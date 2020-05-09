package msg.services.user;

import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.services.AppInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UserServer {

	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new AppInjector());
	
		/*BaseServer server = new BaseServer(8081,injector.getInstance(UserHandlerImpl.class),"UserService");
		server.start();
		server.blockUntilShutdown();
		*/
	}

}