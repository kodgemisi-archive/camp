package tr.org.lkd.lyk2015.camp.repository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.AbstractUser;

@Repository
public class UserDao extends GenericDao<AbstractUser> {

	public AbstractUser findByEmail(String email) {
		Criteria c = this.createCriteria();

		c.add(Restrictions.eq("email", email));
		return (AbstractUser) c.uniqueResult();
	}

}
