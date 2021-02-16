package ord.maia.msauth.jwt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service 
public interface IJwtProvider {

	String createToken(String userName, List<String> roles);
	
	String resolveToken(HttpServletRequest req);
	
	Boolean validateToken(String token);
	
	Authentication getAuthentication(String token);
}
