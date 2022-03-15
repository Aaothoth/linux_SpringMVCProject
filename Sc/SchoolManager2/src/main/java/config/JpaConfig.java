package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;


//在项目新建过程中 maven的依赖分为xml文件和jar包目录
//务必不要多个项目共用一个jar包目录 会产生项目未更新
//在该项目中的版本会失效的引用其他版本的jar包 务必清空依赖
//在选择版本的同时选择相对应的版本 防止旧版本出现兼容性问题
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "dao")
@ComponentScan("model")
public class JpaConfig
{
    //数据源配置
    @Bean(name = "dataSource")
    public BasicDataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tempSchool?" +
                "useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123123");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);

        return dataSource;
    }

    //jdbc模板
    @Bean
    public NamedParameterJdbcTemplate jdbcOperations(BasicDataSource basicDataSource)
    {
        return new NamedParameterJdbcTemplate(basicDataSource);
    }

    //实体管理器工厂
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean lem = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        lem.setDataSource(this.dataSource());
        lem.setJpaVendorAdapter(vendorAdapter);
        lem.setPackagesToScan(new String[]{"model"});
        lem.setJpaDialect(this.jpaDialect());
        lem.setJpaProperties(this.properties());

        return lem;
    }

    //配置方言
    @Bean
    public Properties properties()
    {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

    //配置jpa的支持源 当前为hibernate
    @Bean
    public JpaDialect jpaDialect()
    {
        return new HibernateJpaDialect();
    }

    //配置事务管理器
    @Bean
    public PlatformTransactionManager transactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
