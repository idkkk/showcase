package org.rubik.datasource.views;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Throwables;

/**
 * 公用Controller父类。
 * 
 * <p>@author xiajinxin</p>
 * <p>@since 2013-12-20</p>
 */
public class BaseController {
//
//    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
//
//    @Autowired
//    private JsonUtils jsonUtils;
//
//    /**
//     * 将结果以JSON格式输出。
//     *
//     * @param data 需要进行JSON转换的数据对象。
//     * @param callback 是否是跨站AJAX请求，有该参数则为跨站请求。
//     * @return 响应实体。
//     */
//    protected ResponseEntity<String> renderJson(Object data) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json; charset=utf-8");
//        String responseData = jsonUtils.toJson(data);
//        return new ResponseEntity<String>(responseData, headers, HttpStatus.OK);
//    }
//    
//    /**
//     * 将结果以JSON格式输出。
//     *
//     * @param data 需要进行JSON转换的数据对象。
//     * @param callback 是否是跨站AJAX请求，有该参数则为跨站请求。
//     * @return 响应实体。
//     */
//    protected ResponseEntity<String> renderJsonp(Object data, String callback) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json; charset=utf-8");
//        String responseData = jsonUtils.toJsonByCallback(data, callback);
//        return new ResponseEntity<String>(responseData, headers, HttpStatus.OK);
//    }
//    
    /**
     * 异常处理。
     *
     * @param ex 异常信息。
     * @return ModelMap 出现异常时的返回结果(JSON数据格式)。
     */
    @ExceptionHandler
    @ResponseBody
    public ModelMap handleException(Exception ex) {
//        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));
        ModelMap data = new ModelMap();
        data.put("message", Throwables.getStackTraceAsString(ex));

        return data;
    }
}