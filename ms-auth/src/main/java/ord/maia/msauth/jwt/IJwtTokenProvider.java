package ord.maia.msauth.jwt;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service 
public interface IJwtTokenProvider {

	String createToken(String userName, Set<String> roles);
	
	String resolveToken(HttpServletRequest req);
	
	Boolean validateToken(String token);
	
	Authentication getAuthentication(String token);
}
