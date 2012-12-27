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

	    @At(path = "/tag_group/{tag_group_name}/tag/{tag_name}", types = POST)   
		public void addTagToTagGroup() {
	        TagSynonym tagSynonym = TagSynonym.create(map("name", param("tag_group_name")));
	        tagSynonym.associate("tags")
	                .add(Tag.create(map("name", param("tag_name"))));

	        if (tagSynonym.save()) {
	            render("ok");
	        }
	        render(HttpStatusBadRequest, "fail to save");

	    }
```

Using Curl to test:

```java
curl -XPOST '127.0.0.1:9500/tag_group/java_group/tag/j2ee'
```





