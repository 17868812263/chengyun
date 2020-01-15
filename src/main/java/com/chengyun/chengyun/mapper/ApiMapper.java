package com.chengyun.chengyun.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface ApiMapper {
    List<LinkedHashMap<String,String>> whcljbxx();
    List<LinkedHashMap<String,String>> qyjbxx();
}
