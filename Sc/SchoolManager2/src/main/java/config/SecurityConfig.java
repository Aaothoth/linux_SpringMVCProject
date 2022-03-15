package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import javax.annotation.Resource;

/*@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)*/
public class SecurityConfig //extends GlobalMethodSecurityConfiguration
{
    private BasicDataSource dataSource;

    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(this.dataSource)
                .authoritiesByUsernameQuery("select userNumber, competence from user where userNumber = ?");
    }

    @Autowired()
    public void setDataSource(BasicDataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
