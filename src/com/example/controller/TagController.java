package com.example.controller;

import com.example.model.Tag;
import com.example.model.TagSynonym;
import net.csdn.annotation.rest.At;
import net.csdn.modules.http.ApplicationController;

import java.util.Map;

import static net.csdn.modules.http.RestRequest.Method.POST;
import static net.csdn.modules.http.support.HttpStatus.HTTP_400;
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
}
