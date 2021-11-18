package quizApp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "quiz")
public class Quiz {
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany
	@JoinTable(name = "quiz_answer", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "ansd_id"))
	private Set<Answered> answers = new HashSet<Answered>();
	
	@NotBlank
	private String startDate;
	
	@ManyToOne
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String date) {
		this.startDate = date;
	}

	public Set<Answered> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answered> answers) {
		this.answers = answers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
