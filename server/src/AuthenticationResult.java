package msg.services.auth;

import msg.user.User;

/* 
Builder for sso authentication result from service
*/

public class AuthenticationResult {

	private User user;
	private Status status;

	public AuthenticationResult setUser(User user){
		this.user = user;
		return this;
	}

	public AuthenticationResult setStatus(Status status){
		this.status = status;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Status getStatus(){
		return status;
	}

}