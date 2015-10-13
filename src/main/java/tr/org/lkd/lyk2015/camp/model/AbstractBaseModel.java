package tr.org.lkd.lyk2015.camp.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public class AbstractBaseModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable=false)
	private Calendar creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updateDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar deletionDate;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

	public Calendar getDeletionDate() {
		return deletionDate;
	}

	public void setDeletionDate(Calendar deletionDate) {
		this.deletionDate = deletionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		AbstractBaseModel other = (AbstractBaseModel) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
