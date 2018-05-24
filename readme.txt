使用说明：

1.导入数据库文件nbi.sql
2.修改dbconfig.properties文件中数据库配置信息
3.登陆账号：admin 密码： 123456

开发说明：
1.使用Maven进行项目管理，引入项目常用jar包。
2.在web.xml中配置字符编码过滤器以及对Spring、SpringMVC、MyBatis、Shiro、log4j的支持。
3.使用Spring+SpringMVC+MyBatis搭建后台框架
	--Spring控制事务管理以及controller层、service层、dao层。同时与MyBatis整合配置数据源信息。
	--SpringMVC作为前端控制器，使用过滤器处理页面发送的所有.do的请求。
4.使用Bootstrap作为前端框架
5.使用Shiro进行用户权限管理，此过程涉及异常处理。
6.使用log4j进行日志管理
7.代码通过eclipse Git插件上传至GitHub
8.使用MySQL数据库
9.使用Tomcat服务器
10.使用PlantUML制作流程图

运行过程：
1.启动login.jsp页面。输入正确的账号密码信息，使用ajax发送POST请求，实现异步信息发送。如果系统显示登陆成功，自动跳转到main.jsp页面。如果信息输入有误提示对应错误信息。
2.登陆成功之后创建session，同时设置session的有效时长10s。在此之间，eclipse的控制台打印session的信息。
3.页面跳转到main.jsp之后显示session信息。
4.点击main.jsp的安全退出之后系统才真正退出。在一次登陆成功后，如果不按照此过程退出，再次登陆即使账号密码信息输入有误系统一样可以正常登陆。此过程使用Shiro的logout，系统正常退出后session自动销毁。
5.main.jsp页面10s之内无操作session自动销毁，登陆失效。