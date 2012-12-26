package com.example.controller;

import com.example.model.Tag;
import com.example.model.TagSynonym;
import net.csdn.annotation.rest.At;
import net.csdn.modules.http.ApplicationController;

import static net.csdn.modules.http.RestRequest.Method.POST;
import static net.csdn.modules.http.support.HttpStatus.HttpStatusBadRequest;

/**
 * User: WilliamZhu
 * Date: 12/22/12
 * Time: 5:02 PM
 */
public class TagController extends ApplicationController {

    @At(path = "/tag", types = POST)
    public void saveTag() {
        Tag tag = Tag.create(params());
        tag.save();
        render(tag);
    }

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
}
