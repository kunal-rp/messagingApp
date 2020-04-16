package utils;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import io.grpc.Metadata;
import com.google.protobuf.ByteString;
import java.lang.IllegalArgumentException;
import msg.userhandler.UserDetails;
import java.util.Base64;


public class JwtUtil {

	private static JwtParser parser = Jwts.parser().setSigningKey(Constants.JWT_SIGNING_KEY);

	private JwtUtil(){}

	private void extractUserData(Metadata metadata){
		String value = metadata.get(Constants.AUTHORIZATION_METADATA_KEY);
		if (value == null || !value.startsWith(Constants.BEARER_TYPE)) {
			throw new IllegalArgumentException("Invalid Authorization");
		}
	}

	public static String createJwt(UserDetails userDetails){
		return Jwts.builder()
		.setSubject("users/auth")
		.setExpiration(DateUtils.addMinutes(new Date(), 15))
		.claim("userDetails", Base64.getEncoder().encodeToString(userDetails.toByteArray()))
		.signWith(
			SignatureAlgorithm.HS256,
			Constants.JWT_SIGNING_KEY
			)
		.compact();
	}

	public static Jws<Claims> parseJwt(Metadata metadata){
		String token = metadata.get(Constants.AUTHORIZATION_METADATA_KEY);
		return parser.parseClaimsJws(token);
	}
}