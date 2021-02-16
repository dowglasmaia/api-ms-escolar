package ord.maia.msauth.jwt.impl;

import java.util.Base64;
import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import ord.maia.msauth.jwt.IJwtTokenProvider;

@Slf4j
@Service
public class JwtTokenProvider implements IJwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private String expire;
	
	
	private UserDetailsService  userDetailsService;



	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	
	@Override
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		
		log.info("O Token recebido para o resolve é: " +bearerToken);

		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
		
	
	@Override
	public String createToken(String userName, Set<String> roles) {		
		Claims clains = Jwts.claims().setSubject(userName);
		clains.put("roles", roles);
		
		Date date = new Date();
		Date validate = new Date(date.getTime() + expire );
		
		return Jwts.builder()
				.setClaims(clains)
				.setIssuedAt(date)
				.setExpiration(validate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	@Override
	public Authentication getAuthentication(String token) {		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername( getUserNameFromToken(token) );		
		return new UsernamePasswordAuthenticationToken(userDetails, " ", userDetails.getAuthorities());
	}
	
	@Override
	public Boolean validateToken(String token) {
		try {
			Jws<Claims>claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if( claims.getBody().getExpiration().before(new Date()) ) {
				log.error("O token: " + token +", não é valido");	
				return false;
			}
				log.info("Token válido:");
			return true;
			
		} catch (JwtException | IllegalArgumentException e) {
			log.error(e.getMessage(), e.getCause());		
			return false;
		}
	}


	private String getUserNameFromToken(String token) {
		var userName = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		log.info("UserName encontrado no token é: " +userName);
		return userName;
	}


	
	
}
