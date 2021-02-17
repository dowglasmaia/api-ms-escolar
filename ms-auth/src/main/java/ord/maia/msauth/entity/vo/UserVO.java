package ord.maia.msauth.entity.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	
}
