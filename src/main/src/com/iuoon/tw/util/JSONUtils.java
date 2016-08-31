package com.iuoon.tw.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * jackson 工具类
 *  created by wuyz
 */
public class JSONUtils {

    private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);

	private  static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        //转化不为空的实体
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //允许出现特殊字符和转义符
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
       //允许出现单引号
//        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ;
        return objectMapper;
    }

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj)  {
        try {

			return getInstance().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
            logger.error("对象转json异常{}",e.fillInStackTrace());
		}
        return "";
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz){
        try {
			return getInstance().readValue(jsonStr, clazz);
		} catch (Exception e) {
            logger.error("json转对象异常{}", e.fillInStackTrace());
		} 
        return null;
    }

    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr)
            throws Exception {
        return getInstance().readValue(jsonStr, Map.class);
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
            throws Exception {
        Map<String, Map<String, Object>> map = getInstance().readValue(jsonStr,
                new TypeReference<Map<String, T>>() {
                });
        Map<String, T> result = new HashMap<String, T>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
            throws Exception {
        List<Map<String, Object>> list = getInstance().readValue(jsonArrayStr,
                new TypeReference<List<T>>() {
                });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return getInstance().convertValue(map, clazz);
    }
	

}
