###Tutorial source code

 To get source code about this tutorial,do like this

···shell
   git checkout e3ea13f5398d393a75e51654605646b7fee903c8
···



###Build Model relationship


create A new Model class `com.example.model.TagSynonym`

```java
package com.example.model;

import net.csdn.common.collections.WowCollections;
import net.csdn.jpa.model.Model;

import javax.persistence.OneToMany;
import java.util.List;

import static net.csdn.common.collections.WowCollections.list;

public class TagSynonym extends Model {
    @OneToMany
    private List<Tag> tags= list();
}

```

Modify a exists class `com.example.model.Tag`

```java
package com.example.model;

import net.csdn.jpa.model.Model;

import javax.persistence.ManyToOne;

public class Tag extends Model {
    @ManyToOne
    private TagSynonym tag_synonym;
}
```

At this point, as you can see,we have already build a relationship bettween `Tag` and `TagGroup` .

* Tag=>TagSynonym,ManyToOne    
* TagSynonym=>Tag,OneToMany    

Now,we should do something to test this relationship. We can add A action
in `com.example.controller.TagController`

```java
	   
		@At(path = "/tag_group/{tag_synonym_name}/tag/{tag_name}", types = POST)
		   public void addTagToTagGroup() {
		       TagSynonym tagSynonym = TagSynonym.create(map("name", param("tag_synonym_name")));
	       
			   if(tagSynonym.associate("tags").add(map("name", param("tag_name")));){
				   render(HttpStatusBadRequest, "fail to save");
			   }
	       
			   render("ok");	       

		   }
```

Using Curl to test:

```java
curl -XPOST '127.0.0.1:9500/tag_group/java_group/tag/j2ee'
```

Creating a new Tag for a particular (exsisting or not ) TagSynonym is easier.


There is a magic method (`associate`) in this action.`associate(tags)` will invoke a method in model named "tags" 
which returns a  `net.csdn.jpa.association` object .

When you declare a association, the declaring class automatically gains a related to the association:


To make it more easy and intuitive,try adding code snippets in `com.example.model.TagSynonym` like this:

```java

public Association tags() {
       throw new AutoGeneration();
   }

```
At this point,your action should see like this:

```java

	@At(path = "/tag_group/{tag_synonym_name}/tag/{tag_name}", types = POST)
	   public void addTagToTagGroup() {
	       TagSynonym tagSynonym = TagSynonym.create(map("name", param("tag_synonym_name")));
	       
		   if(tagSynonym.tags().add(map("name", param("tag_name")))){
			   render(HttpStatusBadRequest, "fail to save");
		   }
	       
		   render("ok");	       

	   }
```

To make example simple, we have ignored a lot. So let's make action more elegant and complete.


```java

@At(path = "/tag_group/{tag_synonym_name}/tag/{tag_name}", types = POST)
    public void addTagToTagGroup() {
        Map query = map("name", param("tag_synonym_name"));

        TagSynonym tagSynonym = (TagSynonym) or(
                TagSynonym.where(query).single_fetch(),
                TagSynonym.create(query)
        );

        if (!tagSynonym.tags().add(map("name", param("tag_name")))) {
            render(HTTP_400, tagSynonym.validateResults);
        }
        render("ok save");
    }
```




