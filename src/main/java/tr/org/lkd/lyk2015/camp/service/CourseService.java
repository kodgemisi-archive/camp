package tr.org.lkd.lyk2015.camp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.repository.CourseDao;

@Service
@Transactional
public class CourseService extends GenericService<Course> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CourseDao courseDao;

	public List<Course> getAllActive() {
		return this.courseDao.getAllActive();
	}

	public Course getInstructorsOfCourse(long id) {
		return this.courseDao.getCourseWithInstructors(id);
	}

}
