This time we will talk about how to use service in ServiceFramework.

Step 1: create a interface `com.example.service.TagSuggestService`

```java
@Service(implementedBy = TagSuggestServiceImpl.class)
public interface TagSuggestService {
     public List suggest();
}
```

Step 2: create a implementation `com.example.service.impl.TagSuggestServiceImpl`

```java
public class TagSuggestServiceImpl implements TagSuggestService {
    @Override
    public List suggest() {
        return list(getClass().getName());
    }
}
```

As you can see,you should tell system who implemented the interface using `@Service`
annotation.

Step 3: add a new action .Declare your service,then you can use it in
your action.

```java
@At(path = "/suggest", types = GET)
 public void suggest() {
        render(tagSuggestService.suggest());
 }

 @Inject
 private TagSuggestService tagSuggestService;
```

Done.

