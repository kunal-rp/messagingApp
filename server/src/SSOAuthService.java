package msg.services.auth;

/* 
Auth for all SSO authentication

implementation of auth (using libraries and tools) won't be tested, rather services set up for ease of handling 
*/
public interface SSOAuthService {

	AuthenticationResult getGoogleUserInfo(String accessToken);
	
}