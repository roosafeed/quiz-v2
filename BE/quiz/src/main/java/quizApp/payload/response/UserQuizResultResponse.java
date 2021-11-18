package quizApp.payload.response;

import java.util.HashSet;
import java.util.Set;

public class UserQuizResultResponse {
	private long id;
	
	private String startDate;
	
	private Set<UserQuizQuestionResultResponse> results = new HashSet<UserQuizQuestionResultResponse>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Set<UserQuizQuestionResultResponse> getResults() {
		return results;
	}

	public void setResults(Set<UserQuizQuestionResultResponse> results) {
		this.results = results;
	}
}
