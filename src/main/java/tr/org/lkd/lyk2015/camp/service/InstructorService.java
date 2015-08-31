package tr.org.lkd.lyk2015.camp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.repository.CourseDao;
import tr.org.lkd.lyk2015.camp.repository.InstructorDao;

@Service
@Transactional
public class InstructorService extends GenericService<Instructor> {

	@Autowired
	private InstructorDao instructorDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void create(Instructor instructor, List<Long> ids) {
		List<Course> courses = this.courseDao.getByIds(ids);
		instructor.getCourses().addAll(courses);

		instructor.setPassword(this.passwordEncoder.encode(instructor.getPassword()));

		this.instructorDao.create(instructor);
	}

	public Instructor getInstructorWithCourses(Long id) {
		Instructor instructor = this.instructorDao.getByIdWithCourses(id);
		// Hibernate.initialize(instructor.getCourses());

		return instructor;
	}

	public void update(Instructor instructor, List<Long> ids) {
		List<Course> courses = this.courseDao.getByIds(ids);
		Set<Course> coursesSet = new HashSet<>(courses);
		instructor.setCourses(coursesSet);
		this.update(instructor);
	}

}
