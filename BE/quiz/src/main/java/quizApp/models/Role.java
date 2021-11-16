package quizApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private ERoles name;

	public Role() {}

	public Role(ERoles role) {
		this.name = role;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(ERoles role) {
		this.name = role;
	}
	
	public long getId() {
		return this.id;
	}
	
	public ERoles getName() {
		return this.name;
	}
}
