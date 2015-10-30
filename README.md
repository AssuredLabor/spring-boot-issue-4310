This project demonstrates [Spring-Boot Issue #4310](https://github.com/spring-projects/spring-boot/issues/4310)

## Code works when run from .class files

First run the project from Maven:

    mvn spring-boot:run

In the logs you should see these lines:

```
2015-10-27 17:23:14,862 [org.example.SpringBootStripesEbeans.main()] c.a.e.s.c.BootupClassPathSearch INFO : Classpath search hits in jars[] pkgs[org.example.beans]  searchTime[26]
2015-10-27 17:23:15,220 [org.example.SpringBootStripesEbeans.main()] c.a.e.s.c.DefaultServerFactory WARN : DataSource [db] has autoCommit defaulting to true!
2015-10-27 17:23:15,239 [org.example.SpringBootStripesEbeans.main()] c.a.e.s.c.DefaultServerFactory INFO : DatabasePlatform name:db platform:h2
2015-10-27 17:23:15,338 [org.example.SpringBootStripesEbeans.main()] c.a.e.a.ClassLoadContext DEBUG: Context and Caller ClassLoader's same instance of java.net.URLClassLoader
2015-10-27 17:23:15,400 [org.example.SpringBootStripesEbeans.main()] c.a.e.s.d.BeanDescriptorManager DEBUG: BeanPersistControllers[0] BeanFinders[0] BeanPersistListeners[0] BeanQueryAdapters[0]
2015-10-27 17:23:15,489 [org.example.SpringBootStripesEbeans.main()] c.a.e.s.d.BeanDescriptorManager INFO : Entities enhanced[1]
```

The first and last lines above indicate [Ebeans](http://ebean-orm.github.io/) was able to scan for the class `org.example.beans.User`

When you visit http://localhost:8080/page/welcome you will see the text `Welcome!`


## Code is broken when run as a JAR

Now run the following commands:

```
mvn package
java -jar target/spring-boot-issue-4310-0.0.1-SNAPSHOT.jar
```

In the logs you will see:

```
2015-10-27 17:26:55,536 [main] c.a.e.s.u.ClassPathSearch WARN : No Entities found in ClassPath using ClassPathReader [com.avaje.ebeaninternal.server.util.DefaultClassPathReader@2e166a83] Classpath Searched[[jar
2015-10-27 17:26:55,536 [main] c.a.e.s.c.BootupClassPathSearch INFO : Classpath search hits in jars[] pkgs[]  searchTime[12]
2015-10-27 17:26:55,737 [main] c.a.e.s.c.DefaultServerFactory WARN : DataSource [db] has autoCommit defaulting to true!
2015-10-27 17:26:55,751 [main] c.a.e.s.c.DefaultServerFactory INFO : DatabasePlatform name:db platform:h2
2015-10-27 17:26:55,790 [main] c.a.e.a.ClassLoadContext DEBUG: Context and Caller ClassLoader's same instance of org.springframework.boot.loader.LaunchedURLClassLoader
2015-10-27 17:26:55,827 [main] c.a.e.s.d.BeanDescriptorManager DEBUG: BeanPersistControllers[0] BeanFinders[0] BeanPersistListeners[0] BeanQueryAdapters[0]
2015-10-27 17:26:55,827 [main] c.a.e.s.d.BeanDescriptorManager INFO : Entities enhanced[0]
```

The first and last lines indicate Ebeans was unable to find `org.example.User`

When you visit http://localhost:8080/page/welcome you will get a 500 error in the browser and there will be an exception in your logs:

```
net.sourceforge.stripes.exception.ActionBeanNotFoundException: Could not locate an ActionBean that is bound to the URL [/page/error]. 
Commons reasons for this include mis-matched URLs and forgetting to to implement ActionBean in your class. 
Registered ActionBeans are: {/controller/DefaultView.action/=class net.sourceforge.stripes.controller.DefaultViewActionBean, 
/controller/DefaultView.action=class net.sourceforge.stripes.controller.DefaultViewActionBean}
```

Notice the WelcomeBean and ErrorBean classes are not listed as registered ActionBeans.

