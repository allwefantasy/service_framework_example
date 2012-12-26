package com.example.model;

import net.csdn.jpa.model.Model;

import javax.persistence.ManyToOne;

/**
 * User: WilliamZhu
 * Date: 12/22/12
 * Time: 5:02 PM
 */
public class Tag extends Model {
    @ManyToOne
    private TagSynonym tag_synonym;
}
