
package tr.org.lkd.lyk2015.camp.repository;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.org.lkd.lyk2015.camp.model.AbstractBaseModel;

/**
 * Created by destan on 23.07.2015.
 */
@Repository
public class GenericDao<T extends AbstractBaseModel> {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<T> type;

	protected Logger logger;

	/**
	 * Sets generic type class for this Dao. By setting this we will be able to
	 * call methods that use Criteria without explicit class parameters. <br>
	 * <br>
	 * Note that this method is not optional! You have to use this.
	 *
	 * <br>
	 * <br>
	 *
	 * <strong>If not used:</strong> <br>
	 * <code>
	 * 	session.createCriteria(clazz); // clazz should come as method parameter
	 * </code> <br>
	 * <br>
	 *
	 * <strong>If used:</strong> <br>
	 * <code>
	 * 	session.createCriteria(this.type); // no need for clazz parameter in method
	 * </code>
	 */
	@SuppressWarnings("unchecked")
	public GenericDao() {
		try {
			this.type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		} catch (ClassCastException e) {
			// e.printStackTrace();
			this.type = (Class<T>) AbstractBaseModel.class;
		}

		Class<?> type = this.getClass().getSuperclass();
		this.logger = LoggerFactory.getLogger(type);
	}

	public Long create(final T t) {
		final Session session = this.sessionFactory.getCurrentSession();

		final Calendar now = Calendar.getInstance();
		t.setCreationDate(now);
		t.setUpdateDate(now);

		return (Long) session.save(t);
	}

	@SuppressWarnings("unchecked")
	public T getById(final Long id) {

		final Session session = this.sessionFactory.getCurrentSession();

		return (T) session.get(this.type, id);
	}

	@SuppressWarnings("unchecked")
	public T update(final T t) {

		final Session session = this.sessionFactory.getCurrentSession();

		final Calendar now = Calendar.getInstance();
		t.setUpdateDate(now);

		return (T) session.merge(t);
	}

	public void delete(final T t) {

		final Session session = this.sessionFactory.getCurrentSession();

		t.setDeleted(true);

		session.merge(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {

		final Session session = this.sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(this.type);
		criteria.add(Restrictions.eq("deleted", false));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN);

		return criteria.list();
	}

	public void hardDelete(final T t) {

		final Session session = this.sessionFactory.getCurrentSession();
		session.delete(t);
	}

	public void delete(Long id, Class clazz) {
		final Session session = this.sessionFactory.getCurrentSession();
		this.delete((T) session.load(clazz, id));
	}

	protected Criteria createCriteria() {
		final Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(this.type).add(Restrictions.eq("deleted", false));
	}
}