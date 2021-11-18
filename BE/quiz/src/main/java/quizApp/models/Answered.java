//answers
package quizApp.models;

import javax.persistence.*;

@Entity
@Table(name = "answered")
public class Answered {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinTable(name = "answer_question", joinColumns = @JoinColumn(name = "ansd_id"), inverseJoinColumns = @JoinColumn(name = "que_id"))
	private Questions question;
	
	@ManyToOne
	@JoinTable(name = "answer_choices", joinColumns = @JoinColumn(name = "ansd_id"), inverseJoinColumns = @JoinColumn(name = "ans_id"))
	private Choices chosen;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public Choices getChosen() {
		return chosen;
	}

	public void setChosen(Choices chosen) {
		this.chosen = chosen;
	}
}
