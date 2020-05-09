package msg.services.auth;

import msg.auth.AuthenticationResult;
import java.util.Optional;

public class FakeSSOAuthService implements SSOAuthService {

	private static final AuthenticationResult GENERIC_AUTHENTICATION_RESULT = AuthenticationResult.newBuilder().setStatus(AuthenticationResult.Status.FAIL).build();

	private Optional<AuthenticationResult> desiredAuthenticationResult =  Optional.empty();

	public void setAuthResult(AuthenticationResult desiredAuthenticationResult){
		this.desiredAuthenticationResult = Optional.of(desiredAuthenticationResult);
	}

	public void deleteAll(){
		this.desiredAuthenticationResult = Optional.empty();
	}

	@Override
	public AuthenticationResult getGoogleUserInfo(String accessToken){
		if(desiredAuthenticationResult.isPresent()){
			return desiredAuthenticationResult.get();
		}
		return GENERIC_AUTHENTICATION_RESULT;
	}
	
}