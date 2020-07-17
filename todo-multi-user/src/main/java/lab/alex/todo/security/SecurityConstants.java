package lab.alex.todo.security;

public class SecurityConstants {
	
	public static final String SIGH_UP_URL = "/api/users";
	
	public static final long TOKEN_EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_SECRET = "nr923h98fh292fn8jsd";
	
	

}
