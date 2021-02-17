package ord.maia.msauth.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourcesExceptionHandler {	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> runtimeExceptionError(BadCredentialsException e, HttpServletRequest request) {			
		StandardError error = StandardError.builder()
				.timestamp(System.currentTimeMillis())
				.status(HttpStatus.UNAUTHORIZED.value())
				.error("Autenticação falhou")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.build();		
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(error);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> runtimeExceptionError(UsernameNotFoundException e, HttpServletRequest request) {			
		StandardError error = StandardError.builder()
				.timestamp(System.currentTimeMillis())
				.status(HttpStatus.NOT_FOUND.value())
				.error("Recurso não encontrado")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.build();		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
	}
}
