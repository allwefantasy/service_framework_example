package com.example.service;

import com.example.service.impl.TagSuggestServiceImpl;
import net.csdn.annotation.Service;

import java.util.List;

/**
 * 1/6/13 WilliamZhu(allwefantasy@gmail.com)
 */
@Service(implementedBy = TagSuggestServiceImpl.class)
public interface TagSuggestService {
     public List suggest();
}
