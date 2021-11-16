package quizApp.repo;

import org.springframework.stereotype.Repository;

import quizApp.models.Questions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Long> {
	Optional<Questions> findById(long id);
}
