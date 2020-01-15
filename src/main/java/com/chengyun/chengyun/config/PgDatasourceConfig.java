package com.chengyun.chengyun.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.chengyun.chengyun.mapperpg",sqlSessionTemplateRef = "pgSqlSessionTemplate")
public class PgDatasourceConfig {

    @Bean(name = "pgDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDatasource(){
        //        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "pgSqlSessionFactory")
    
    public SqlSessionFactory pgSqlSessionFactory(@Qualifier("pgDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapperpg/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "pgDataSourceTransactionManager")
    
    public DataSourceTransactionManager pgDataSourceTransactionManager(@Qualifier("pgDatasource") DataSource dataSource ){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "pgSqlSessionTemplate")
    
    public SqlSessionTemplate pgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
