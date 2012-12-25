###clone repository:

```shell
git clone git://github.com/allwefantasy/ServiceFramework.git ServiceFramework
```


###Database Configuration
- Create a database named `wow` in mysql server
- Create tables by running `wow.sql` file which in `sql` directory

Check mysql port ,username,and password in configuration file like follows:

```
config/application.yml 
```

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


###Do some boring things.

* Remove all packages in `src`,`test`.DO NOT DELETE `META-INF` in `src`
* Copy `jar/service_framework.jar` to lib directory
* Import to your preferred IDE。I recommend you Idea Intellij (Community version)

###Coding

Create a new Model class         
`com.example.model.Tag`

```java
public class Tag extends Model {
}
```

Notice that you can declare fileds manually otherwise ServiceFramework will auto generate fields for you.


HmHm ,now create a controller   
`com.example.controller.TagController`

```java

public class TagController extends ApplicationController {

    @At(path = "/tag", types = RestRequest.Method.POST)
    public void saveTag() {
        Tag tag = Tag.create(params());
        render(tag);
    }
}

```

Create a startup Class 

`com.example.MyFirstApplication`

```java
public class MyFirstApplication extends Application {
    public static void main(String[] args) {
        ServiceFramwork.scanService.setLoader(MyFirstApplication.class);
        Application.main(args);
    }
}
```

Run in your IDE. Now you can access your Controller using `curl`

```java
curl -XPOST 'http://127.0.0.1:9500/tag' -d 'name=java'
```

All done .Check whether the data have been persisted in tag table.
