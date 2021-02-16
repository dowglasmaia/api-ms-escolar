package ord.maia.msauth.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ord.maia.msauth.jwt.IJwtTokenProvider;
import ord.maia.msauth.repository.UserRepository;
import ord.maia.msauth.services.IAuthService;

@Log4j2
@Service
public class AuthService implements IAuthService {

	private final AuthenticationManager authenticationManager;
	private final IJwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, IJwtTokenProvider jwtTokenProvider,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}

	@Override
	public Map<Object, Object> login(String userName, String password) {
		var user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException(userName + ", não encontrado"));
		var token = " ";

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

			token = jwtTokenProvider.createToken(userName, user.getRoles());

			Map<Object, Object> model = new HashMap<>();
			model.put("username", userName);
			model.put("token", token);

			return model;
		} catch (Exception e) {
			log.info("Error ao tentar fazer Login: " + e.getMessage(), e.getCause());
			throw new BadCredentialsException("Usuario e/ou Senha Inválido(s)", e.getCause());
		}
	}

}
