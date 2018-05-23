使用说明：

1.修改dbconfig.properties文件中数据库配置信息
2.登陆账号：admin 密码：       123456

开发说明：
1.使用Maven进行项目管理，引入项目常用jar包。
2.在web.xml中配置字符编码过滤器以及对Spring、SpringMVC、MyBatis的支持。
3.使用Spring+SpringMVC+MyBatis搭建后台框架
	--Spring控制事务管理以及controller层、service层、dao层。同时与MyBatis整合配置数据源信息。
	--SpringMVC作为前端控制器，使用过滤器处理页面发送的所有.do的请求。
3.使用Bootstrap作为前端框架
4.使用Shiro进行用户权限管理，此过程涉及异常处理。
5.代码通过eclipse Git插件上传至GitHub
6.使用MySQL数据库
7.使用Tomcat服务器

运行过程：
1.启动login.jsp页面。输入正确的账号密码信息，使用ajax实现异步信息发送。如果系统显示登陆成功，自动跳转到main.jsp页面。如果信息输入有误提示对应错误信息。
2.登陆成功之后创建session，同时设置session的有效时长。
3.页面跳转到main.jsp之后显示session信息。
4.点击main.jsp的安全退出之后系统才真正退出。在一次登陆成功后，如果不按照此过程退出，再次登陆即使账号密码信息输入有误系统一样可以正常登陆。