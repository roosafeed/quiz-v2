package quizApp.payload.response;

import java.util.HashSet;
import java.util.Set;

public class UserQuestionResponse {
	private long id;
	private String question;
	
	private Set<UserChoicesResponse> choices = new HashSet<UserChoicesResponse>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<UserChoicesResponse> getChoices() {
		return choices;
	}

	public void setChoices(Set<UserChoicesResponse> choices) {
		this.choices = choices;
	}
}
