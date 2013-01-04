Filters are powerful,flexible.
Unlike struts or Servlet  ,filters in ServiceFramework are just 
`methods`(not class,and no need to configure in xml) that are run before
,'around' a controller action(action also a method in `controller`).

Filters are inherited,so if you set a filter on ApplicationController,it will be run on every
controller in your application.

We have a `com.example.controller.TagController`. Most of the time, we will
put some similar function in the same controller.
Suppose we will add a tag to a exist tagSynonym.

```java



   @At(path = "/tag_synonym/{tag_synonym_name}/tag/{tag_name}", types = POST)
   public void addTagToTagSynonym() {

       if (!_tagSynonym.tags().add(map("name", param("tag_name")))) {
           render(HTTP_400, _tagSynonym.validateResults);
       }
       render("ok save");
   }



  private TagSynonym _tagSynonym;

  /*
   this means findTagSynonym is a before filter and only intercept  action addTagToTagSynonym
  */
  @BeforeFilter
  private final static Map $findTagSynonym=map(only,list("addTagToTagSynonym"));

  private void findTagSynonym() {
       _tagSynonym = TagSynonym.where(map("name", param("tag_synonym_name"))).single_fetch();
       if (_tagSynonym == null) {
		   //you can simply use render halt the request cycle
           render(HttpStatusBadRequest, param("tag_synonym_name") + " not exits");
       }
   }
   
```

Every request having his owe Controller instance means `Action` like addTagToTagSynonym is
thread-safe. And you can directly use instance variable assigned by filter. However,don't
define too much  instance variable which will make you confusing.

ServiceFramework for now support  `BeforeFilter` and `AroundFilter`





