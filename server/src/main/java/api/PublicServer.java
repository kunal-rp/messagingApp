package msg.api.server;

import io.grpc.ServerServiceDefinition;
import io.grpc.ServerInterceptors;
import msg.services.AppInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.concurrent.TimeUnit;
import msg.api.login.LoginImpl;
import msg.api.sample.SampleImpl;
import io.grpc.Server;
import utils.BaseServer;
import utils.BaseServerBuilder;
import utils.AuthInterceptor;


public class PublicServer {

	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new AppInjector());

		//ServerServiceDefinition intercepted = ServerInterceptors.intercept(new UserHandlerImpl(), new AuthInterceptor());
		BaseServer server = new BaseServerBuilder(8081, "PublicServer")
			.addService(injector.getInstance(LoginImpl.class))
			.addService(ServerInterceptors.intercept(new SampleImpl(), new AuthInterceptor()))
			.build();
		server.start();
		server.blockUntilShutdown();		
	}
}