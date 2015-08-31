package tr.org.lkd.lyk2015.camp.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.Student;

@Repository
public class StudentDao extends GenericDao<Student> {

	public Student getUserByTckn(Long tckn) {
		Criteria criteria = this.createCriteria();

		criteria.add(Restrictions.eq("tckn", tckn));

		return (Student) criteria.uniqueResult();
	}
}
