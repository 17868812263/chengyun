package com.chengyun.chengyun.controller;

import com.alibaba.fastjson.JSON;
import com.chengyun.chengyun.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping("vehicle/basicinfo")
    public LinkedHashMap<String,Object> whcljbxx() {
        List<LinkedHashMap<String,String>> list=null;
        LinkedHashMap<String,Object> resultMap=new LinkedHashMap<>();
        LinkedHashMap<String,Object> map=new LinkedHashMap<>();
        try {
            resultMap.put("code","200");
            resultMap.put("msg","数据获取成功");
            list=apiService.whcljbxx();
            map.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code","500");
            resultMap.put("msg","数据获取失败");
        }
        resultMap.put("data",map);
        return resultMap;
    }

    @RequestMapping("enterprise/basicinfo")
    public LinkedHashMap<String,Object> qyjbxx() {
        List<LinkedHashMap<String,String>> list=null;
        LinkedHashMap<String,Object> resultMap=new LinkedHashMap<>();
        LinkedHashMap<String,Object> map=new LinkedHashMap<>();
        try {
            resultMap.put("code","200");
            resultMap.put("msg","数据获取成功");
            list=apiService.qyjbxx();
            map.put("list",list);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code","500");
            resultMap.put("msg","数据获取失败");
        }
        resultMap.put("data",map);
        return resultMap;
    }
}
