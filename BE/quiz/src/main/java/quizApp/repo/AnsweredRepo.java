package quizApp.repo;

import org.springframework.stereotype.Repository;

import quizApp.models.Answered;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AnsweredRepo extends JpaRepository<Answered, Long> {
	Optional<Answered> findById(long id);
}
