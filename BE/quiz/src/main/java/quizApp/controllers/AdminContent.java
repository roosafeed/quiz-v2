package quizApp.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import quizApp.models.Choices;
import quizApp.models.Questions;
import quizApp.payload.request.ChoiceAddRequest;
import quizApp.payload.request.QuestionAddRequest;
import quizApp.payload.response.MessageResponse;
import quizApp.repo.ChoicesRepo;
import quizApp.repo.QuestionsRepo;
import quizApp.repo.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminContent {
	@GetMapping("/test")
	public String adminAccess() {
		return "Admin Works.";
	}

	@Autowired
	UserRepository urepo;

	@Autowired
	QuestionsRepo qrepo;

	@Autowired
	ChoicesRepo crepo;

	@PostMapping("/question/add")
	public ResponseEntity<MessageResponse> addQuestion(@Valid @RequestBody QuestionAddRequest que) {
		String msg = "Question added";

		Questions q = new Questions();
		Set<Choices> cs = new HashSet<Choices>();

		q.setQuestion(que.getQuestion());

		for (ChoiceAddRequest cr : que.getChoices()) {
			Choices c = new Choices();
			c.setIsAnswer(cr.getIsAnswer());
			c.setName(cr.getName());

			Choices csaved = crepo.save(c);

			cs.add(csaved);
		}

		q.setChoices(cs);

		qrepo.save(q);

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(msg));
	}

	@GetMapping("/question/all")
	public ResponseEntity<List<Questions>> viewAll() {
		List<Questions> qs = qrepo.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(qs);
	}
}
