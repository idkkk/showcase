package org.rubik.datasource.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;

/**
 * <p>Json工具类(目前内置Jackson2的实现)。</p>
 * 
 * <p>@author xiajinxin</p>
 * <p>@since 2013-12-26</p>
 */
//@Component
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final char PAREN_LEFT_CHAR = '(';
    private static final char PAREN_RIGHT_CHAR = ')';

    /**
     * Json处理对象。
     */
//    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 将对象转换为Json字符串。
     * @param object 需要进行转换的对象。
     * @return String Json字符串。
     */
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));
            throw Throwables.propagate(ex);
        }
    }

    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));
            throw Throwables.propagate(ex);
        }
    }
    
    /**
     * 根据callback参数来决定生成不同的Json数据(AJAX跨域请求JSON数据)
     * @param callback 如果有callback参数值，则表示跨域请求JSON，需要生成JSONP格式数据；否则为传统JSON格式数据。
     * @param object 需要进行转换的对象。
     * @return json字符串。
     */
    public String toJsonByCallback(Object object, String callback) {
        if (Strings.isNullOrEmpty(callback)) { //非跨域请求
            return toJson(object);
        } else {
            StringBuilder sb = new StringBuilder(300);
            sb.append(callback);
            sb.append(PAREN_LEFT_CHAR);
            sb.append(toJson(object));
            sb.append(PAREN_RIGHT_CHAR);
            return sb.toString();
        }
    }
}