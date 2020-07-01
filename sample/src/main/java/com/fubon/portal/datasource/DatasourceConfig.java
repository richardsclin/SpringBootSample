package com.fubon.portal.datasource;


import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import com.fubon.datasource.DatasourceConfigBase;

@Configuration
@MapperScan(basePackages = {"com.fubon.portal"})
public class DatasourceConfig extends DatasourceConfigBase{
	
	@Override
	@Primary
	@Bean(name = "odsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() throws Exception {
		return super.dataSource();
	}
	
	@Override
	@Bean(name = "sqlSessionFactory")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception
    {
		return super.sqlSessionFactory(dataSource);
    }
}
