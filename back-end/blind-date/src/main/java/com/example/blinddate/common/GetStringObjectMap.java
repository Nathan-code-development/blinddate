package com.example.blinddate.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetStringObjectMap {

    public static Map<String, Object> getStringObjectMap(List<?> list){
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("code", 200);
        map.put("message", "success");
        map.put("total", list.size());
        map.put("data", list);
        return map;
    }
}
