###下载项目:

```shell
git clone git://github.com/allwefantasy/ServiceFramework.git ServiceFramework
```


###数据库配置
- 在你的mysql中新建一个库，名称为：wow
- 运行sql目录下的 wow.sql,把所有的表建好。

这应该就是所有准备工作了。但是您的端口可能不是默认的3306,所以您还应该检查下配置文件，并且修改username和password

```
config/application.yml 
```
文件中的

```yaml
development:
    datasources:
        mysql:
           host: 127.0.0.1
           port: 3306
           database: wow
           username: root
           password: root
```


###一些小准备

* 删除src,test目录下所有的package.
* 将文件夹 `jar` 中的service_framework.jar 拷贝进 lib目录
* 然后，将项目导入进你喜欢的IDE中。我推荐使用 Idea Intellij Community 版本。

###开始代码

新建一个模型类     
`com.example.model.Tag`

```java
public class Tag extends Model {
}
```

注意，你也可以申明模型类的属性，默认他会到名称为`tag`的表中获取信息。


建一个controller   
`com.example.controller.TagController`

```java

public class TagController extends ApplicationController {

	@At(path = "/tag", types = RestRequest.Method.POST)
    public void saveTag() {
	   Tag tag = Tag.create(params());
       if(tag.save()){
           render(ok("tag saved"));
       }
       else {
           render(fail(JSONArray.fromObject(tag.validateResults).toString()));
       }

    }
  

}

```

新建一个启动类

`com.example.MyFirstApplication`

```java
public class MyFirstApplication extends Application {
    public static void main(String[] args) {
        ServiceFramwork.scanService.setLoader(MyFirstApplication.class);
        Application.main(args);
    }
}
```

在IDE运行该类，现在你可以访问你刚才写的controller了。

```java
curl -XPOST 'http://127.0.0.1:9400/tag_group' -d 'name=java'
```

这个时候你可以查看数据库，应该就有相应的记录了。







