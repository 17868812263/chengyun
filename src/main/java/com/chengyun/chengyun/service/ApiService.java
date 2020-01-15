package com.chengyun.chengyun.service;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface ApiService {
    /**
     * 危化车辆基本信息接口
     * @return
     */
    List<LinkedHashMap<String,String>> whcljbxx();
    /**
     * 企业基本信息接口
     * @return
     */
    List<LinkedHashMap<String,String>> qyjbxx();

}
