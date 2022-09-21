#图书馆管理系统
##功能介绍
1.图书的增删改查接口
2.登录
3.鉴权
##技术栈介绍
1.springBoot
2.h2
3.shiro
4.mybatis-plus
5.swagger
##测试流程
1.下载源代码
2.maven下载依赖jar包
3.启动项目
4.访问swagger，地址：http://localhost:8080/swagger-ui.html
5.登陆前，图书的增删改查接口都没有权限访问
6.提供两个用户账号：
          账号1：a/a;只拥有查询图书的权限。
          账号2：b/b; 拥有增删改查图书的权限。

