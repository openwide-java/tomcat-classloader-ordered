# tomcat-classloader-ordered

A classloader for Tomcat 8 which loads the jar of WEB-INF lib in alphabetical order

## How to use

You need to put the jar in Tomcat's lib directory.

Then, you need to add a `src/main/webapp/META-INF/context.xml` (supposing you use Maven but you get the idea) to your project:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<Context>
	<Loader loaderClass="fr.openwide.tomcat.catalina.loader.WebappOrderedClassLoader" />
</Context>
```
