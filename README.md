Gemini
======

###可配置项
1. 内置的模板地址，可通过设置TEMPLATE_PATH来进行重新指定
2. 内置的日志文件地址，可通过设置LOG_PATH来进行重新指定

		public class BssModule extends EmptyModule {
    		public BssModule() {
        		TEMPLATE_PATH = "/Users/ray/workspaces/mobile-bss/webapps/WEB-INF/pages";
        		LOG_PATH = "/Users/ray/workspaces/mobile-bss/webapps/logs/bss.log";
    		}
		}

3. 默认提供Hibernate的Session管理，Hibernate的配置文件可以通过module的绑定hibernate.configfile参数来进行指定

		bind(String.class).annotatedWith(Names.named("hibernate.configlog")).toInstance("/hibernate.cfg.xml")


###使用方式
1. gemini-0.1.jar放入到web application的类加载目录下面，如WEB-INF/lib
2. 增加Jersey的Application入口
3. 在Application入口中完成Module的初始并调用Gemini的init方法初始化系统，如Gemini.instance.init()
4. 继承AbstractGate写资源访问， 如果要用view，并且将request等托管到Gemini，需要在每个资源初始到时候调用init()
5. web.xml配置Jersey访问Servlet
6. 浏览器访问即可