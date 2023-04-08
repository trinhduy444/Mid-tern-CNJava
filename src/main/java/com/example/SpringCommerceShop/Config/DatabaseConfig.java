package com.example.SpringCommerceShop.Config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
	@Value("${db.datasource.driver-class-name}")
	private String driverClass;
	
	@Value("${db.datasource.url}")
	private String url;
	
	@Value("${db.datasource.username}")
	private String username;
	
	@Value("${db.datasource.password}")
	private String password;
	
	private String secrectKey = "DuyDepTrai@123";
    @Bean(name = "dataSource")
    public DataSource dataSource() {
    	HikariConfig dataConfig = new HikariConfig();
        
    	dataConfig.setDriverClassName(driverClass);
    	dataConfig.setJdbcUrl(AES.decrypt(url, secrectKey));
    	dataConfig.setUsername(AES.decrypt(username, secrectKey));
    	dataConfig.setPassword(AES.decrypt(password, secrectKey));
        
        HikariDataSource hikariDataSource = new HikariDataSource(dataConfig);
        return hikariDataSource;
    }
    
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
    	return new DataSourceTransactionManager(dataSource());
    }
    
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception{
    	final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();	
    	sessionFactory.setDataSource(dataSource);
    
    	sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/example/SpringCommerceShop/Mapper/Sql/*.xml"));
    	
    	return sessionFactory.getObject();
    }
    
    
}
