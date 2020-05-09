package utils;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import java.util.logging.Logger;


public class Constants {
    public static final String JWT_SIGNING_KEY = "L8hHXsaQOUjk5rg7XPGv4eL36anlCrkMz8CJ0i/8E/0=";
    public static final String BEARER_TYPE = "Bearer";

    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("JWTTOKEN", Metadata.ASCII_STRING_MARSHALLER);
    public static final Context.Key<Object> USER_DETAILS_CONTEXT_KEY = Context.key("userDetails");

    private Constants() {
        throw new AssertionError();
    }
}