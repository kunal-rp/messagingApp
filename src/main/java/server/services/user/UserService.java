package msg.services.user;

import msg.user.User;
import msg.user.UserDetails;


/* 
it would be fine for another grpc service to create a stub and make a call to the user service ;

this is to show one way to encapsulate the calls s.t. there is tight control on who is calling the services and abstracting the boiler plate away
*/
public interface UserService {


	//simple service to get the full user details from the user details ( from JWT)
	User getUserFromUserDetails(UserDetails userDetails);
	
}