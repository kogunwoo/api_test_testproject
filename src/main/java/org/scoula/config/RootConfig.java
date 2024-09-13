package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration // 스프링 설정 파일임을 나타냅니다.
//@ComponentScan(basePackages = {"org.scoula.board.service" })
//@MapperScan(basePackages = {"org.scoula.board.mapper"})
@PropertySource({"classpath:/application.properties"}) // application.properties 파일에서 속성을 로드합니다.
public class RootConfig {

    // application.properties 파일에서 해당 값을 주입받습니다.
   // @Value("${jdbc.driver}")
    String driver="com.mysql.cj.jdbc.Driver";

    //@Value("${jdbc.url}")
    String url="jdbc:mysql://localhost:3306/product";

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    // 데이터베이스 연결을 위한 DataSource Bean을 정의합니다.
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource= new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/product?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setAutoCommit(true);

        return dataSource;

    }

    // ApplicationContext를 자동으로 주입받습니다.
    @Autowired
    ApplicationContext applicationContext;

    // MyBatis의 SqlSessionFactory를 생성하는 Bean을 정의합니다.
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        // MyBatis 설정 파일의 위치를 지정합니다.
        sqlSessionFactory.setConfigLocation(
                applicationContext.getResource("classpath:mybatis-config.xml"));

        // 데이터베이스 연결을 위한 DataSource를 설정합니다.
        sqlSessionFactory.setDataSource(dataSource());

        // SqlSessionFactory 객체를 반환합니다.
        return sqlSessionFactory.getObject();
    }

    // DataSourceTransactionManager를 생성하는 Bean을 정의합니다.
    @Bean
    public DataSourceTransactionManager transactionManager() {
        // DataSourceTransactionManager 객체를 생성하고 DataSource를 설정합니다.
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());

        // DataSourceTransactionManager 객체를 반환합니다.
        return manager;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
