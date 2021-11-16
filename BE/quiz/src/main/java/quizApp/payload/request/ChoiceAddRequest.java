package quizApp.payload.request;

import javax.validation.constraints.*;

public class ChoiceAddRequest {
	@NotBlank
	@Size(max = 255)
	private String name;

	@Size(max = 1)
	private String isAnswer; // 'y' or 'n'

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}
}
