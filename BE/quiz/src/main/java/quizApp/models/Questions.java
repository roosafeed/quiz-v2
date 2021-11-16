package quizApp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Questions {
	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Size(max = 255)
	private String question;
	
	@OneToMany
	@JoinTable(name = "question_choices", joinColumns = @JoinColumn(name = "que_id"), inverseJoinColumns = @JoinColumn(name = "ans_id"))
	private Set<Choices> choices = new HashSet<Choices>();

	
	public Questions() {}
	
	public Questions(String q) {
		this.question = q;
	}
	
	//choices
	public Set<Choices> getChoices() {
		return choices;
	}

	public void setChoices(Set<Choices> choices) {
		this.choices = choices;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
