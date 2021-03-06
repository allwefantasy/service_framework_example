package com.example.controller;

import com.example.model.Tag;
import com.example.model.TagSynonym;
import com.example.service.TagSuggestService;
import com.google.inject.Inject;
import net.csdn.annotation.filter.BeforeFilter;
import net.csdn.annotation.rest.At;
import net.csdn.modules.http.ApplicationController;

import java.util.Map;

import static net.csdn.modules.http.RestRequest.Method.GET;
import static net.csdn.modules.http.RestRequest.Method.POST;
import static net.csdn.modules.http.support.HttpStatus.HTTP_400;
import static net.csdn.modules.http.support.HttpStatus.HttpStatusBadRequest;

/**
 * User: WilliamZhu
 * Date: 12/22/12
 * Time: 5:02 PM
 */
public class TagController extends ApplicationController {

    @At(path = "/suggest", types = GET)
    public void suggest() {
        render(tagSuggestService.suggest());
    }

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

    @BeforeFilter
    private final static Map $findTagSynonym = map("only", list("addTagToTagSynonym"));

    @At(path = "/tag_synonym/{tag_synonym_name}/tag/{tag_name}", types = POST)
    public void addTagToTagSynonym() {

        if (!_tagSynonym.tags().add(map("name", param("tag_name")))) {
            render(HTTP_400, _tagSynonym.validateResults);
        }
        render("ok save");
    }

    private TagSynonym _tagSynonym;

    private void findTagSynonym() {
        _tagSynonym = TagSynonym.where(map("name", param("tag_synonym_name"))).single_fetch();
        if (_tagSynonym == null) {
            render(HttpStatusBadRequest, param("tag_synonym_name") + " not exits");
        }
    }

    @Inject
    private TagSuggestService tagSuggestService;
}
