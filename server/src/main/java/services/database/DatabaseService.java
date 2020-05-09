package msg.services.database;

import msg.user.User;

public interface DatabaseService {

	void createUserIfNeeded(User user);
	
}