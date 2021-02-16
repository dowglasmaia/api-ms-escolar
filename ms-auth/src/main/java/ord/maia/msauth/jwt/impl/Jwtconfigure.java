package ord.maia.msauth.jwt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ord.maia.msauth.jwt.IJwtTokenProvider;

public class Jwtconfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final IJwtTokenProvider jwtTokenProvider;

	@Autowired
	public Jwtconfigure(IJwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(jwtTokenProvider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
