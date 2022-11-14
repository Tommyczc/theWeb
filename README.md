## theWeb

#### 前言:
本项目为实习项目,初次学习spring MVC框架,许多代码都有重复或者漏洞,之后会慢慢改进。

**介绍一下该项目的内容:** 
* 该项目基于spring MVC框架写的,前后端完全分离,后端是Java,前端是js和html(虽然我写的是jsp,但是语法一样的),前端通过ajax异步加载向后端请求数据。
* 该项目有登录以及注册功能,用户可以自己上传图片然后查看并且删除自己的上传记录。
* 管理员可以对用户信息进行增删改查操作,同时可以查看并且删除图片上记录。
* 此项目采用RBAC权限模型,安全框架是Shiro,该项目有两个权限角色,[user, admin],顾名思义,同一个html文档,admin可以看到user全部页面,但是user不能看到admin的内容 (可以看项目运行结果自行体会,懒得上传图片了)。
* 还写了个自定义图片url拦截器,项目的静态资源(用户上传的图片)存储在 “src/main/resources/static/image”正常来说静态资源只要在浏览器输入url就能访问,该拦截器的作用是拦截所有图片链接请求,只有当前用户和管理员可见。
* 由于时间有点赶,部分内容没写,例如加密,全部都明文存储,还有对用户注册时密码的一些筛查,后续有时间再加。

**主要框架(具体可见项目里的pom.xml)**
* shiro-spring 版本1.4.0
* mysql-connector-java 版本8.0.29
* spring-boot-starter-web
* mybatis 版本3.5.11

**项目初始化**
1. 按照找到sql文件 src/main/SQL/theWeb.sql ,并且在数据库加载文件生成theWeb数据库。
2. 管理员账号:tommy 密码:12345
3. 用户账号:lily 密码:12345
4. 数据库配置文件在 “src/main/resources/config/MyBatisCfg.xml”, Ip地址在这行 `<property name="url" value="jdbc:mysql://127.0.0.1:3306/theweb"/>`
5. 确保Idea安装了Spring Initializr

***good luck***