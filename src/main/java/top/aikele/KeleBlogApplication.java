package top.aikele;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.Properties;

@SpringBootApplication
public class KeleBlogApplication {
    @Bean
    public DefaultAdvisorAutoProxyCreator createDefaultConfig() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KeleBlogApplication.class, args);
    }

}
