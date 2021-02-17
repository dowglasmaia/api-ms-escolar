package ord.maia.msauth.config.security;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface IAuthService {

	 Map<?, ?>  login(String userName, String password);
}
