package com.app.library.OAuth;

public class Constants {
    public static final String OAUTH_TOKEN = "/oauth/token";
    public static final String CLIENT_ID="my-trusted-client";
    public static final String REALM="MY_OAUTH_REALM";
    public static final String RESOURCE_ID = "my_rest_api";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 30000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
