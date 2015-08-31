package tr.org.lkd.lyk2015.camp.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Instructor;

@Repository
public class InstructorDao extends GenericDao<Instructor> {

	public Instructor getByIdWithCourses(Long id) {
		Criteria c = createCriteria();
		
		c.add(Restrictions.idEq(id));
		c.setFetchMode("courses", FetchMode.JOIN);
		
		return (Instructor) c.uniqueResult();
	}
	
}
