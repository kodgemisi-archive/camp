package tr.org.lkd.lyk2015.camp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * This entity represents an application form which is submitted by a Student.
 *
 * Same student may submit different forms in different years.
 *
 */
@Entity
public class Application extends AbstractBaseModel {

	public enum WorkStatus {
		WORKING, STUDENT, NOT_WORKING
	}

	@Min(2012)
	@Column(nullable = false)
	private Integer year;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WorkStatus workStatus;

	@Column(nullable = false)
	private Boolean officer = false;

	private String corporation;

	private String workDetails;

	@Column(nullable = false)
	private Integer englishLevel = 0;

	private String githubLink;

	@Column(nullable = false)
	private Boolean validated = false;

	@Column(nullable = false)
	private String validationId;

	@ManyToMany
	@Size(min = 1, max = 3)
	private Set<Course> preferredCourses = new HashSet<>();

	@ManyToOne(optional = false)
	private Student owner;

	private Boolean needAccomodation = true;

	private Boolean selected = false;

	public WorkStatus getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public boolean isOfficer() {
		return this.officer;
	}

	public void setOfficer(boolean officer) {
		this.officer = officer;
	}

	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getWorkDetails() {
		return this.workDetails;
	}

	public void setWorkDetails(String workDetails) {
		this.workDetails = workDetails;
	}

	public int getEnglishLevel() {
		return this.englishLevel;
	}

	public void setEnglishLevel(int englishLevel) {
		this.englishLevel = englishLevel;
	}

	public String getGithubLink() {
		return this.githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public Set<Course> getPreferredCourses() {
		return this.preferredCourses;
	}

	public void setPreferredCourses(Set<Course> preferredCourses) {
		this.preferredCourses = preferredCourses;
	}

	public boolean isNeedAccomodation() {
		return this.needAccomodation;
	}

	public void setNeedAccomodation(boolean needAccomodation) {
		this.needAccomodation = needAccomodation;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getOfficer() {
		return this.officer;
	}

	public void setOfficer(Boolean officer) {
		this.officer = officer;
	}

	public Student getOwner() {
		return this.owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

	public void setEnglishLevel(Integer englishLevel) {
		this.englishLevel = englishLevel;
	}

	public Boolean getValidated() {
		return this.validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public String getValidationId() {
		return this.validationId;
	}

	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}

	public Boolean getSelected() {
		return this.selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
