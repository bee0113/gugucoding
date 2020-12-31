package org.zerock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
@ComponentScan(basePackages = {"org.zerock.sample", "org.zerock.service"})
@MapperScan(basePackages = {"org.zerock.mapper"})
//@EnableTransactionManagement // 트랜잭션 관리: <tx:anntation-driven>
public class RootConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        //        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        //        hikariConfig.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/springbook");
        /*log4jdbc 설정 시*/
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mariadb://localhost:3306/springbook");
        /*log4jdbc 설정 시 end*/
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(300000);

        // test Query
        hikariConfig.setConnectionTestQuery("SELECT now() FROM dual");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "200");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }


}
