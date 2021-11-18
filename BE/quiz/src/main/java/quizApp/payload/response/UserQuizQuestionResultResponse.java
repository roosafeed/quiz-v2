package quizApp.payload.response;

import java.util.HashSet;
import java.util.Set;

import quizApp.models.Choices;

public class UserQuizQuestionResultResponse {
	private long id, chosen;
	
	private String question;
	
	private Set<Choices> choices = new HashSet<Choices>();
	
	private boolean isCorrect;

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

	public Set<Choices> getChoices() {
		return choices;
	}

	public void setChoices(Set<Choices> choices) {
		this.choices = choices;
	}

	public long getChosen() {
		return chosen;
	}

	public void setChosen(long chosen) {
		this.chosen = chosen;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
