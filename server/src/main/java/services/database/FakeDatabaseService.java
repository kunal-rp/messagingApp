package msg.services.database;

import java.util.Optional;
import msg.user.User;
import java.util.ArrayList;

public class FakeDatabaseService implements DatabaseService {

	
	private ArrayList<User> allUsers = new ArrayList<>();
	private Optional<User> createdUser = Optional.empty();

	public Optional<User> getCreatedUser(){
		return createdUser;
	}

	public void addExistingUser(User user){
		allUsers.add(user);
	}

	public void deleteAll(){
		allUsers = new ArrayList<>();
		createdUser = Optional.empty();
	}

	@Override
	public void createUserIfNeeded(User user){
		if(!allUsers.stream().filter(existingUser -> existingUser.getUserId() == user.getUserId()).findAny().isPresent()){
			createdUser = Optional.of(user);
		}
	}
	
}