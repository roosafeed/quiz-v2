package quizApp.payload.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

public class QuestionAddRequest {
	@NotBlank
	@Size(max = 255)
	private String question;
	
	private Set<ChoiceAddRequest> choices = new HashSet<ChoiceAddRequest>();

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<ChoiceAddRequest> getChoices() {
		return choices;
	}

	public void setChoices(Set<ChoiceAddRequest> choices) {
		this.choices = choices;
	}
}
