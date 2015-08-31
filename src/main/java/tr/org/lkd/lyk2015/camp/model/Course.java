package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Course extends AbstractBaseModel {

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@NotEmpty
	private String prerequisites;

	private String detailPageLink;

	@NotNull
	private Boolean active = true;

	@ManyToMany(mappedBy = "courses")
	private Set<Instructor> instructors = new HashSet<>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrerequisites() {
		return this.prerequisites;
	}

	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}

	public String getDetailPageLink() {
		return this.detailPageLink;
	}

	public void setDetailPageLink(String detailPageLink) {
		this.detailPageLink = detailPageLink;
	}

	public Set<Instructor> getInstructors() {
		return this.instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
