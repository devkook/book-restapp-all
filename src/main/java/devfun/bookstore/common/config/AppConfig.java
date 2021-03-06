
package devfun.bookstore.common.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@EnableTransactionManagement
@MapperScan("devfun.bookstore.common.mapper")
@ComponentScan(basePackages = {"devfun.bookstore.common.service"}, useDefaultFilters = false, includeFilters = {@Filter(Service.class)})
@Configuration
public class AppConfig implements TransactionManagementConfigurer  {

	// @Bean
	// public DataSource dataSource() {
	// 	return new EmbeddedDatabaseBuilder()
	// 			.setName("testdb") // DB 이름 설정
	// 			.setType(EmbeddedDatabaseType.HSQL) // DB 종류 설정
	// 			.addScript("schema.sql") // 스키마 스크립트 추가
	// 			.addScript("data.sql") // 데이터 스크립트 추가
	// 			.build();
	// }
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
	        String driverClass = "com.mysql.jdbc.Driver";
	        String url = "jdbc:mysql://us-cdbr-cb-east-01.cleardb.net:3306/cb_devkook";
	        String username = "bf2680dae9d429";
	        String password = System.getProperty("db_passwd");
	
	        BoneCPDataSource ds = new BoneCPDataSource();
	
	        ds.setDriverClass(driverClass);
	        ds.setJdbcUrl(url);
	        ds.setUsername(username);
	        ds.setPassword(password);
        	return ds;
    	}
	

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager () {
		return transactionManager(); // reference the existing @Bean method above
	}

}
