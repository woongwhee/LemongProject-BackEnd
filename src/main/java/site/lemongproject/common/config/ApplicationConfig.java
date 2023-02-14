package site.lemongproject.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@EnableTransactionManagement
@EnableScheduling
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "site.lemongproject")
public class ApplicationConfig {
    @Autowired private ApplicationContext applicationContext;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
        return factoryBean.getObject();
    }
    @Bean
    public SqlSession sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public ObjectMapper objectMapper(){

        return new ObjectMapper();
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public DataSource dataSource(){
        Properties properties=new Properties();
        String filePath=ApplicationConfig.class.getResource("/security/dataSource.properties").getPath();
        try {
            properties.load(new InputStreamReader(new FileInputStream(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setDefaultAutoCommit(false);
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
        dataSource.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        return dataSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
