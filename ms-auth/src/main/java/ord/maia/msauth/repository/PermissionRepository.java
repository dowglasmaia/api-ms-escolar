package ord.maia.msauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ord.maia.msauth.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	@Query("SELECT p FROM Permission p WHERE p.description =:description")
	Optional<Permission>findByDescription(@Param("description") String description);

}
