package sample.spring.mvc.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 設定
 *
 */
@Configuration
@PropertySource({ "classpath:config.properties", "classpath:datasource.properties" })
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigure() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * DB接続情報
	 *
	 * @param driverClassName
	 * @param url
	 * @param userName
	 * @param password
	 * @return
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource(@Value("${datasource.driver.class.name}") String driverClassName,
			@Value("${datasource.url}") String driverUrl,
			@Value("${datasource.username}") String driverUsername,
			@Value("${datasource.password}") String driverPassword) {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(driverUrl);
		dataSource.setUsername(driverUsername);
		dataSource.setPassword(driverPassword);

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {

		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));

		return sessionFactoryBean;
	}

	@Bean
	public MessageSource messageSource() {

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");

		return messageSource;
	}

	@Bean
	public Properties propertiesConfig(@Value("${sample.config}") String sampleConfig) {

		Properties properties = new Properties();
		properties.setSampleConfig(sampleConfig);

		return properties;
	}
}
