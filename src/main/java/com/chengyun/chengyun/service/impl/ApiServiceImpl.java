package com.chengyun.chengyun.service.impl;

import com.chengyun.chengyun.mapper.ApiMapper;
import com.chengyun.chengyun.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public List<LinkedHashMap<String, String>> whcljbxx() {

        return apiMapper.whcljbxx();
    }

    @Override
    public List<LinkedHashMap<String, String>> qyjbxx() {
        return apiMapper.qyjbxx();
    }
}
