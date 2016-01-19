如何使用
======

修改tomcat配置
============
```
$ vim $CATALINA_HOME/conf/context.xml
```


```
<?xml version='1.0' encoding='utf-8'?>
<Context>

    <Valve className="com.radiadesign.catalina.session.RedisSessionHandlerValve" />
    <Manager className="com.radiadesign.catalina.session.RedisSessionManager"
             host="localhost"
             port="6379"
             database="0"
             maxInactiveInterval="60" />

</Context>
```