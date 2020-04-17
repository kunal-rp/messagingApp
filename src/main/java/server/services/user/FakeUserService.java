package msg.services.user;

import msg.user.User;
import msg.user.UserDetails;
import com.google.inject.Singleton;
import com.google.inject.Inject;

@Singleton
public class FakeUserService implements UserService {
	
	int num = 999;

	public void setUserId(int id){
		num = id;
	}

	@Override
	public User getUserFromUserDetails(UserDetails userDetails){
		return User.newBuilder().setUserId(num).setEmail("FAKE").build();
	}
	
}
