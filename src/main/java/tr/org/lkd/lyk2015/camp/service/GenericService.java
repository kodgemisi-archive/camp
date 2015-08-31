
package tr.org.lkd.lyk2015.camp.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.model.AbstractBaseModel;
import tr.org.lkd.lyk2015.camp.repository.GenericDao;

@Transactional
public abstract class GenericService<T extends AbstractBaseModel> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger logger;

	public GenericService() {
		Class<?> type = this.getClass().getSuperclass();
		this.logger = LoggerFactory.getLogger(type);
	}

	@Autowired
	protected GenericDao<T> genericDao;

	public Long create(final T t) {

		if (t == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return this.genericDao.create(t);
	}

	public T getById(final Long id) {

		if (id == null) {
			throw new RuntimeException("Id cannot be null");
		}

		return this.genericDao.getById(id);
	}

	public T update(final T t) {

		if (t == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return this.genericDao.update(t);
	}

	public void delete(final T t) {

		if (t == null) {
			throw new RuntimeException("Model cannot be null");
		}

		this.genericDao.delete(t);
	}

	public void delete(final Long id) {

		if (id == null) {
			throw new RuntimeException("id cannot be null");
		}

		this.genericDao.delete(this.getById(id));
	}

	public List<T> getAll() {

		return this.genericDao.getAll();
	}

	public void hardDelete(final T t) {

		if (t == null) {
			throw new RuntimeException("Model cannot be null");
		}

		this.genericDao.delete(t);
	}
}