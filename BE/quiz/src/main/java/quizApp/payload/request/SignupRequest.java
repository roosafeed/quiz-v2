package quizApp.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
	@NotBlank
	@Size(max = 25, min = 3)
	private String name;

	@NotBlank
	@Size(max = 35)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	@Size(max = 40, min = 4)
	private String password;

	// name
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// email
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	// password
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	// role
	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
