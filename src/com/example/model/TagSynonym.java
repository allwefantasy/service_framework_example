package com.example.model;

import net.csdn.common.collections.WowCollections;
import net.csdn.jpa.model.Model;

import javax.persistence.OneToMany;
import java.util.List;

import static net.csdn.common.collections.WowCollections.list;

/**
 * 12/26/12 WilliamZhu(allwefantasy@gmail.com)
 */
public class TagSynonym extends Model {
    @OneToMany
    private List<Tag> tags= list();
}
