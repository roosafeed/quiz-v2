package quizApp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "choices")
public class Choices {
	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Size(max = 255)
	private String name;

	@NotBlank
	@Size(max = 1)
	private String isAnswer; // 'y' or 'n'
	
	public Choices() {}
	
	public Choices(String n, String i) {
		this.name = n;
		this.isAnswer = i;
	}

	// Id
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	// name
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	//isAnswer
	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}
	
	public String getIsAnswer() {
		return this.isAnswer;
	}
}
