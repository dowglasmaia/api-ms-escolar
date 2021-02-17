package ord.maia.msauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ord.maia.msauth.config.security.IAuthService;
import ord.maia.msauth.entity.vo.UserVO;

@RestController
@RequestMapping("/login")
public class AuthController {

	private final IAuthService authService;

	@Autowired
	public AuthController(IAuthService authService) {
		this.authService = authService;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
				 consumes = {"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> login(@RequestBody UserVO userVO) {
		
		var modelLogin = authService.login(userVO.getUserName(), userVO.getPassword());
		
		return ResponseEntity.ok(modelLogin);		
	}
	
	
	@RequestMapping("/testeSecurityAuth")
	public String teste() {
		return "Não pode acessar este endpoint, sem estar autenticado.";
	}

}
