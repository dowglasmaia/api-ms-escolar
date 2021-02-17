package ord.maia.msauth.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "accountNonExpired")
	private Boolean accountNonExpired;

	@Column(name = "accountNonLocked")
	private Boolean accountNonLocked;

	@Column(name = "credentialsNonExpired")
	private Boolean credentialsNonExpired;

	@Column(name = "enabled")
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", 
		joinColumns ={@JoinColumn(name ="id_user")},
		inverseJoinColumns = {@JoinColumn(name="id_permission")})
	private Set<Permission> permissoes = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}
	
	public Set<String> getRoles(){
		
		Set<String>roles = new HashSet<>();
		
		this.permissoes.stream()
			.forEach( p -> {
				roles.add(p.getDescription());
			});		
		return roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
