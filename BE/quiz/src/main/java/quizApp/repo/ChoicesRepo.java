package quizApp.repo;

import org.springframework.stereotype.Repository;

import quizApp.models.Choices;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChoicesRepo extends JpaRepository<Choices, Long> {
	Optional<Choices> findById(long id);
}
