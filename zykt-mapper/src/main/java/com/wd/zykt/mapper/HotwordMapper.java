package com.wd.zykt.mapper;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface HotwordMapper {

    /**
     * 查询热搜词
     * @return
     */
    public ArrayList<String> selHotWord();

    /**
     * 添加新增热搜词
     * @return
     */
    public int insHotWord(String hotword);
}
