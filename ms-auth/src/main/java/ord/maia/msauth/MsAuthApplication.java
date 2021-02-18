package ord.maia.msauth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ord.maia.msauth.entity.Permission;
import ord.maia.msauth.entity.User;
import ord.maia.msauth.repository.PermissionRepository;
import ord.maia.msauth.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
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

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder passwordEncoder) {

		boolean permission = permissionRepository.findByDescription("Admin").isPresent();

		System.out.println(permission);
		Permission p1 = null;
		if (!permission) {
			 p1 = new Permission(null, "Admin");
			 p1 = permissionRepository.save(p1);
		} else {
			 p1 = permissionRepository.findByDescription("Admin").get();
		}

		boolean user = userRepository.findByUserName("dowglasmaia").isPresent();
		if (!user) {
			User admin = new User();
			admin.setUserName("dowglasmaia");
			admin.setAccountNonExpired(true);
			admin.setAccountNonLocked(true);
			admin.setCredentialsNonExpired(true);
			admin.setEnabled(true);
			admin.setPassword(passwordEncoder.encode("123456"));
			
			admin.getPermissoes().add(p1);
			
			userRepository.save(admin);

		} else {
			var admin = userRepository.findByUserName("dowglasmaia").get();
		}

	}

}
