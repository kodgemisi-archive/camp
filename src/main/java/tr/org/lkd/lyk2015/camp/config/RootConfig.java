package tr.org.lkd.lyk2015.camp.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"tr.org.lkd.lyk2015.camp.repository", "tr.org.lkd.lyk2015.camp.service"})
@PropertySource("classpath:application-${spring.profiles.active:dev}.properties")
public class RootConfig {

    @Autowired
    private Environment environment;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverClass"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		return dataSource;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.scanPackages("tr.org.lkd.lyk2015.camp.model");
		sessionBuilder.addProperties(this.getHibernateProperties());

		return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getProperty("db.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("db.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getProperty("db.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getProperty("db.hibernate.format_sql"));
		properties.put("hibernate.use_sql_comments", environment.getProperty("db.hibernate.use_sql_comments"));
		// properties.put("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}

}
