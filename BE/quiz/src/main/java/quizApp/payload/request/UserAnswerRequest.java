package quizApp.payload.request;

import javax.validation.constraints.NotBlank;

public class UserAnswerRequest {
	@NotBlank
	private long qid, cid;

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}
}
