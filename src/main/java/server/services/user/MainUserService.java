package msg.services.user;

import msg.user.User;
import msg.user.UserDetails;
import msg.services.user.UserHandlerImpl;
import msg.userhandler.UserHandlerGrpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;
import com.google.inject.Singleton;

public class MainUserService implements UserService {

	//port defined in user server
	private final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081").usePlaintext(true).build();
	private UserHandlerGrpc.UserHandlerBlockingStub blockingStub = UserHandlerGrpc.newBlockingStub(channel);

	public User getUserFromUserDetails(UserDetails userDetails){

		return blockingStub.getUserFromDetails(userDetails);
	}
	
}
