package com.example.theweb.security;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

@Configuration
public class ShiroConfig {



//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//
//        // logged in users with the 'admin' role
//        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
//
//        // logged in users with the 'document:read' permission
//        chainDefinition.addPathDefinition("/guest/**", "authc");
//
//        // all other paths require a logged in user
//        //chainDefinition.addPathDefinition("/**", "authc");
//        return chainDefinition;
//    }
    /**
     * Shiro自带的过滤器，可以在这里配置拦截页面
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager securityManager) {

        //初始化一个ShiroFilter工程类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        Map<String, Filter> filterObjMap = new LinkedHashMap<>();
        filterObjMap.put("imageFilter", new imageFilter()); //匿名访问静态资源
        shiroFilterFactoryBean.setFilters(filterObjMap);



        //我们知道Shiro是通过SecurityManager来管理整个认证和授权流程的，这个SecurityManager可以在下面初始化
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();

         //Shiro过滤器常用的有如下几种
        filterMap.put("/login", "anon");

        filterMap.put("/fail", "anon");
        //filterMap.put("/api/user/notics", "authc");
        //注意，roles[user,admin]对应的用户是 《同时》 拥有这两种身份的用户
        filterMap.put("/admin/**", "roles[admin]");
        filterMap.put("/guest/**", "authc");
        //filterMap.put("/public/**", "anon"); //匿名访问静态资源
        filterMap.put("/image/**", "imageFilter"); //匿名访问静态资源



        //filterMap.put("/**", "authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //用户没登录被拦截后，当然需要调转到登录页了，这里配置登录页
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }



    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



    /**
     * SecurityManager管理认证、授权整个流程
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Autowired UserRealm userRealm) {

        //新建一个SecurityManager供ShiroFilterFactoryBean使用
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //SecurityManager既然管理认证等信息，那他就需要一个类来帮他查数据库吧。这就是Realm类
        securityManager.setRealm(userRealm);
        //System.out.println("userRealm state:  "+userRealm);
        return securityManager;
    }

    /**
     * 自定义Realm，当SecurityBean需要来查询相关权限信息时，需要有Realm代劳
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
