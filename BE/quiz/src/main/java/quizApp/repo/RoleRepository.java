package quizApp.repo;

import org.springframework.stereotype.Repository;

import quizApp.models.ERoles;
import quizApp.models.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERoles name);
}
