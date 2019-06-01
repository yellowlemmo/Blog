package com.cui.blog.demo;

import com.cui.blog.demo.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        /**
         * SpringBoot整合Elasticsearch在项目启动前设置，防止报错
         * 解决netty冲突后初始化client时还抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
