package quizApp.repo;

import org.springframework.stereotype.Repository;

import quizApp.models.Quiz;
import quizApp.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {
	Optional<Quiz> findById(long id);
	
	Optional<List<Quiz>> findAllByUser(User u);
}
