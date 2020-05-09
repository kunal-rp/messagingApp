package msg.services.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import msg.auth.AuthenticationResult;

/* 
Auth for all SSO authentication

implementation of auth (using libraries and tools) won't be tested, rather services set up for ease of handling 
*/
public interface SSOAuthService {

	AuthenticationResult getGoogleUserInfo(String accessToken);
	
}