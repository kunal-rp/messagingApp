package msg.services.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import msg.auth.AuthenticationResult;
import msg.user.User;
import java.util.Optional;
import java.util.Collections;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.codec.binary.StringUtils;

public class MainSSOAuthService implements SSOAuthService {

	private final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
    .setAudience(Collections.singletonList(Secrets.SSO_GOOGLE_CLIENT_ID))
    .build();


	@Override
	public AuthenticationResult getGoogleUserInfo(String accessToken)  {	

		try{
		GoogleIdToken idToken = verifier.verify(accessToken);
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			// check that email is verified Boolean.valueOf(payload.getEmailVerified());

			return AuthenticationResult.newBuilder()
				.setStatus(AuthenticationResult.Status.SUCCESS)
				.setUser(
					User.newBuilder()
					.setUserId((String) payload.getUserId())
					.setName((String) payload.get("name"))
					.setEmail((String) payload.getEmail()))
				.build();
		}
		}catch(Exception e){
			System.out.println(e);
		}

		return AuthenticationResult.newBuilder().setStatus(AuthenticationResult.Status.FAIL).build();
	}
	
}