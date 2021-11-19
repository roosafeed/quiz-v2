package quizApp.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RestController;

import quizApp.models.Answered;
import quizApp.models.Choices;
import quizApp.models.Questions;
import quizApp.models.Quiz;
import quizApp.models.User;
import quizApp.payload.request.NewQuizRequest;
import quizApp.payload.request.UserAnswerRequest;
import quizApp.payload.response.MessageResponse;
import quizApp.payload.response.UserChoicesResponse;
import quizApp.payload.response.UserQuestionResponse;
import quizApp.payload.response.UserQuizQuestionResultResponse;
import quizApp.payload.response.UserQuizResultResponse;
import quizApp.repo.AnsweredRepo;
import quizApp.repo.ChoicesRepo;
import quizApp.repo.QuestionsRepo;
import quizApp.repo.QuizRepo;
import quizApp.repo.UserRepository;
import quizApp.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserContent {
	@GetMapping("/test")
	public String userAccess() {
		return "User Content.";
	}
	
	@Autowired
	QuestionsRepo qrepo;
	
	@Autowired
	QuizRepo quizrepo;
	
	@Autowired
	ChoicesRepo crepo;
	
	@Autowired
	AnsweredRepo ansrepo;
	
	@Autowired
	UserRepository urepo;
	
	//Get question and its choices by id
	@GetMapping("/quiz/{qzid}/question/get/{id}")
	public ResponseEntity<UserQuestionResponse> getOneQuestion(@PathVariable long qzid, @PathVariable long id) {
		UserQuestionResponse res = new UserQuestionResponse();
		
		if(quizrepo.existsById(qzid)) {
			Questions que = qrepo.getOne(id);
			
			Set<UserChoicesResponse> choices = new HashSet<UserChoicesResponse>();		
			
			for(Choices c: que.getChoices()) {
				UserChoicesResponse choice = new UserChoicesResponse();
				choice.setId(c.getId());
				choice.setName(c.getName());
				
				choices.add(choice);
			}
			
			res.setId(que.getId());
			res.setQuestion(que.getQuestion());
			res.setChoices(choices);
		}	
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	//Get a list of all questions
	@GetMapping("/quiz/{id}/question/get/all")
	public ResponseEntity<Set<Long>> getAllQuestionId(@PathVariable long id) {
		Set<Long> qids = new HashSet<Long>();
		List<Questions> ques = qrepo.findAll();
		boolean qz = quizrepo.existsById(id);
		
		if(!ques.isEmpty() && qz) {
			for(Questions q: ques) {
				qids.add(q.getId());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(qids);
	}
	
	//start new quiz and get quiz id
	@PostMapping("/quiz/new")
	public ResponseEntity<Long> startQuiz(@Valid @RequestBody NewQuizRequest newQuiz) {
		Object udo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetailsImpl udi = (UserDetailsImpl) udo;
		User u = urepo.findById(udi.getId()).orElseThrow(() -> new RuntimeException("User not found."));
		
		Quiz quiz = new Quiz();
		quiz.setStartDate(newQuiz.getStartDate());
		quiz.setUser(u);
		
		quiz = quizrepo.save(quiz);
				
		return ResponseEntity.status(HttpStatus.OK).body(quiz.getId());
	}
	
	//Submit an answer
	@PostMapping("/quiz/{id}/answer/add")
	public ResponseEntity<MessageResponse> addAnswer(@PathVariable long id, @Valid @RequestBody UserAnswerRequest ans) {
		MessageResponse res = new MessageResponse("ok");
		
		if(quizrepo.existsById(id)) {
			Answered answer = new Answered();
			Questions q = qrepo.getOne(ans.getQid());
			Choices c = crepo.getOne(ans.getCid());
			Quiz quiz = quizrepo.getOne(id);
			
			Set<Answered> answers = quiz.getAnswers();
			
			boolean found = false;
			for(Answered a: answers) {
				if(a.getQuestion().getId() == ans.getQid()) {
					found = true;					
					a.setChosen(c);
					answer = ansrepo.save(a);	
					res.setMessage("Answer changed");
				}
			}
			
			if(!found) {
				answer.setChosen(c);
				answer.setQuestion(q);
				
				answer = ansrepo.save(answer);
				
				answers.add(answer);				
			}		
			
			quiz.setAnswers(answers);
			quizrepo.save(quiz);
		}
		else {
			res.setMessage("Quiz does not exist. Start a new quiz.");
		}
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/quiz/result/all")
	public ResponseEntity<Set<UserQuizResultResponse>> getAllQuizResult() {
		Object udo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetailsImpl udi = (UserDetailsImpl) udo;
		User u = urepo.findById(udi.getId()).orElseThrow(() -> new RuntimeException("User not found."));
		
		Optional<List<Quiz>> quizes = quizrepo.findAllByUser(u);
		
		Set<UserQuizResultResponse> res = new HashSet<UserQuizResultResponse>();
		
		if(quizes.isPresent()) {
			for(Quiz q: quizes.get()) {
				UserQuizResultResponse qres = new UserQuizResultResponse();
				qres.setId(q.getId());
				qres.setStartDate(q.getStartDate());
				Set<UserQuizQuestionResultResponse> qresults = new HashSet<UserQuizQuestionResultResponse>();
				
				for(Answered a: q.getAnswers()) {
					UserQuizQuestionResultResponse qr = new UserQuizQuestionResultResponse();
					qr.setQuestion(a.getQuestion().getQuestion());
					qr.setChosen(a.getChosen().getId());
					qr.setChoices(a.getQuestion().getChoices());
					qr.setId(a.getQuestion().getId());
					if(a.getChosen().getIsAnswer().equalsIgnoreCase("y")) {
						qr.setCorrect(true);
					}
					else {
						qr.setCorrect(false);
					}
					
					qresults.add(qr);
				}				
				
				qres.setResults(qresults);
				res.add(qres);
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/quiz/{id}/result")
	public ResponseEntity<UserQuizResultResponse> getOneQuizResult(@PathVariable long id) {
		Object udo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetailsImpl udi = (UserDetailsImpl) udo;
		User u = urepo.findById(udi.getId()).orElseThrow(() -> new RuntimeException("User not found."));
		
		Optional<Quiz> quizes = quizrepo.findById(id);
		
		UserQuizResultResponse qres = new UserQuizResultResponse();
		
		if(quizes.isPresent()) {
			Quiz q = quizes.get();
			if(q.getUser().getId() == u.getId()) {				
				qres.setId(q.getId());
				qres.setStartDate(q.getStartDate());
				Set<UserQuizQuestionResultResponse> qresults = new HashSet<UserQuizQuestionResultResponse>();
				
				for(Answered a: q.getAnswers()) {
					UserQuizQuestionResultResponse qr = new UserQuizQuestionResultResponse();
					qr.setQuestion(a.getQuestion().getQuestion());
					qr.setChosen(a.getChosen().getId());
					qr.setChoices(a.getQuestion().getChoices());
					qr.setId(a.getQuestion().getId());
					if(a.getChosen().getIsAnswer().equalsIgnoreCase("y")) {
						qr.setCorrect(true);
					}
					else {
						qr.setCorrect(false);
					}
					
					qresults.add(qr);
				}

				qres.setResults(qresults);
			}			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(qres);
	}
	
}
