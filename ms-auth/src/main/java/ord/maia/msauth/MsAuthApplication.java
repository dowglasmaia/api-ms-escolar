package ord.maia.msauth;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ord.maia.msauth.entity.Permission;
import ord.maia.msauth.entity.User;
import ord.maia.msauth.repository.PermissionRepository;
import ord.maia.msauth.repository.UserRepository;

@SpringBootApplication
public class MsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, passwordEncoder);
		};

	}

	private void initUsers(
			UserRepository userRepository, 
			PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		
		Permission permission = null;
		
		Permission findPermission = permissionRepository.findByDescription("Admin").get();
		if (findPermission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = findPermission;
		}
		
		User admin = new User();
		admin.setUserName("dowglasmaia");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.getPermissoes().addAll(Arrays.asList(permission));

		User find = userRepository.findByUserName("dowglasmaia").get();
		if (find == null) {
			userRepository.save(admin);
		}
	}

}
