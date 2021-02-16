package ord.maia.msauth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ord.maia.msauth.repository.UserRepository;
import ord.maia.msauth.services.IAuthService;

public class UserServices implements UserDetailsService {

	private final UserRepository userRepository;
	

	@Autowired
	public UserServices(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username).orElseThrow(
				() -> new UsernameNotFoundException("Usuario não encontrado para o UserName: " + username));
	}


	

	

}
