###Gemini
=========
#####Gemini是什么
```
这是一套可以快速建立站点的程序，叫它程序而不是框架是因为我觉得它还不够好，不够完善。
你可以将Gemin用于任何的地方，集成在任何的框架中，无论是Spring，Struts还是其他什么。

```


#####功能
```
1. 日志
2. 请求上下文管理
3. 数据模型管理
4. 视图管理
5. 数据库会话管理
6. 数据库访问模板
```

#####用法
**在看下面用法的时候，我想你应该对Guice有一个了解和认识，比如什么叫Module，什么是Provider，什么是Injector等等。**

在我的项目里，我是用jersey做的REST服务，当然你也可以不用，不管怎样，我们都需要先对Gemini做一个初始化。
		
		Gemini.instance.init();
		
好了，现在Gemini就可以用了，做了初始化动作后，我们拥有了日志管理器，随时随地都可以使用logger进行日志的输出了：

		Gemini.instance.getLogger(“Gemini”)

或者先对Gemini进行注入
		
		@Inject
		private Gemini gemini;
		gemini.getLogger("Gemini");

对于一个系统，我们还需要什么呢？当然是数据访问！Gemini默认提供Hibernate的访问操作，今后我们会增强不同的数据操作接口:

首先，我们先要对Hibernate的会话管理器进行初始化:
		
		public MySessionContext extends AbstractSessionContext {
			private static final MySessionContext msc = new MySessionContext();
			
			private MySessionContext() {
				init("my hibernate config");
			}
			
			public static MySessionContext getMSC() {
				return msc;
			}
		}
		
随后，生命一个Annotations来进行该会话管理器的声明
	
		@BindingAnnotation
		@Target({FIELD, PARAMETER, METHOD})
		@Retention(RUNTIME)
		public @interface MYSession {}
		
最后，在我们的Module里写上@Provides：
	
		@Provides
		@Singleton
		@MYSession
		public SessionContext provideMySessionContext() {
			return MySessionContext.getMSC();
		}
		
在使用会话管理器的时候，只要在方法里注入时加上@MYSession 即可获得MySessionContext对象。

你想开始从数据库中获得数据了吗？
只需要继承HibernateAccess就好了！

		public class MyAccess extends HibernateAccess {
			public MyAccess(@MYSession SessionContext sessionContext) {
				super(sessionContext);
				setClass(Entity.class)
			}
		}
		
我们需要写增删改查的操作吗？不需要！HibernateAccess已经将这些操作进行了封装，只需要调用即可。

有了数据，我们怎么去展示呢？
你的Servlet或者资源访问需要继承AbstractGate

		public IndexGate extends AbstractGate {
			public IndexGate() {
				super();
			}
		}		
		
好了，此刻，我们拥有了数据模型，视图工厂，请求上下文三样东西，要进行展示的时候，我们只需要调用:

		viewFactory.render(geminiContext, "index"); // 第二个参数是模板名称
		
That's all.
Good luck for you!
		