package com.example.service.impl;

import com.example.service.TagSuggestService;
import java.util.List;

import static net.csdn.common.collections.WowCollections.list;

/**
 * 1/6/13 WilliamZhu(allwefantasy@gmail.com)
 */

public class TagSuggestServiceImpl implements TagSuggestService {
    @Override
    public List suggest() {
        return list(getClass().getName());
    }
}
