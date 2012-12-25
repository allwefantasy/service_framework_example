package com.example.controller;

import com.example.model.Tag;
import net.csdn.annotation.rest.At;
import net.csdn.modules.http.ApplicationController;
import net.csdn.modules.http.RestRequest;

/**
 * User: WilliamZhu
 * Date: 12/22/12
 * Time: 5:02 PM
 */
public class TagController extends ApplicationController {

    @At(path = "/tag", types = RestRequest.Method.POST)
    public void saveTag() {
        Tag tag = Tag.create(params());
        render(tag);
    }
}
