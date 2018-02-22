# tomcat-classloader-ordered

A classloader for Apache Tomcat 8 which loads the jars of WEB-INF lib in alphabetical order.

Prior to version 8, Apache Tomcat loaded the jars of the WEB-INF lib directory in alphabetical order. Starting with version
8, the order is not predictable anymore and can lead to erratic behaviors, especially when deploying applications in a
clustered environment.

This classloader delegates all its operation to the original Tomcat classloader with the only difference that it loads the
jars in alphabetical order, guaranteeing a reproducible deployment.

The code is very simple and should be (hopefully!) bug free.

## Compatibility

This classloader should be compatible with Apache Tomcat 8.0.x and 8.5.x.

## How to use

You need to put the jar in Tomcat's lib directory.

Then, you need to add a `src/main/webapp/META-INF/context.xml` (supposing you use Maven but you get the idea) to your project:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<Context>
	<Loader loaderClass="fr.openwide.tomcat.catalina.loader.WebappOrderedClassLoader" />
	<Resources className="fr.openwide.tomcat.catalina.loader.OrderedStandardRoot"/>
</Context>
```

That's all!

## License

This project is licensed under the Apache License version 2.0.
