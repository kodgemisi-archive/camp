package tr.org.lkd.lyk2015.camp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Instructor extends AbstractUser {

	@ManyToMany
	private Set<Course> courses = new HashSet<>();

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("INSTRUCTOR");
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(simpleGrantedAuthority);
		return list;
	}
}
