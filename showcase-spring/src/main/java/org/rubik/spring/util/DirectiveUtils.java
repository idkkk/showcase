/**
 * Copyright ©2008-2012 Lafaso.com All Rights Reserved. 
 */
package org.rubik.spring.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * <p>Freemarker宏工具类。</p>
 * 
 * <p>@author xiajinxin</p>
 * <p>@since 2014-01-09</p>
 */
public abstract class DirectiveUtils {

    /**
     * 将params的值复制到variable中
     *
     * @param env
     * @param params
     * @return 原Variable中的值
     * @throws TemplateException
     */
    public static Map<String, TemplateModel> addParamsToVariable(
            Environment env, Map<String, TemplateModel> params)
            throws TemplateException {
        Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
        if (params.size() <= 0) {
            return origMap;
        }
        Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
        String key;
        TemplateModel value;
        for (Map.Entry<String, TemplateModel> entry : entrySet) {
            key = entry.getKey();
            value = env.getVariable(key);
            if (value != null) {
                origMap.put(key, value);
            }
            env.setVariable(key, entry.getValue());
        }
        return origMap;
    }

    /**
     * 将variable中的params值移除
     *
     * @param env
     * @param params
     * @param origMap
     * @throws TemplateException
     */
    public static void removeParamsFromVariable(Environment env,
                                                Map<String, TemplateModel> params,
                                                Map<String, TemplateModel> origMap) throws TemplateException {
        if (params.size() <= 0) {
            return;
        }
        for (String key : params.keySet()) {
            env.setVariable(key, origMap.get(key));
        }
    }

    /**
     * 获得RequestContext
     * <p/>
     * ViewResolver中的exposeSpringMacroHelpers必须为true
     *
     * @param env
     * @return
     * @throws TemplateException
     */
    public static RequestContext getContext(Environment env)
            throws TemplateException {
        TemplateModel ctx = env
                .getGlobalVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
        if (ctx instanceof AdapterTemplateModel) {
            return (RequestContext) ((AdapterTemplateModel) ctx)
                    .getAdaptedObject(RequestContext.class);
        } else {
            throw new TemplateModelException("RequestContext '"
                    + AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE
                    + "' not found in DataModel.");
        }
    }

    /**
     * 取得字符串
     *
     * @param name 名称
     * @return 返回字符串
     * @throws TemplateException 抛出模板异常
     */
    public static String getString(String name,
                                   Map<String, TemplateModel> params) throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) model).getAsString();
        } else if ((model instanceof TemplateNumberModel)) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        } else {
            throw new RuntimeException(name);
        }
    }

    /**
     * 取得长整型整数
     *
     * @param name 名称
     * @return 返回长整型
     * @throws TemplateException 抛出模板异常
     */
    public static Long getLong(String name, Map<String, TemplateModel> params)
            throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (Strings.isNullOrEmpty(s)) {
                return null;
            }
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                throw Throwables.propagate(e);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().longValue();
        } else {
        	throw new RuntimeException(name);
        }
    }

    /**
     * 取得整数
     *
     * @param name 名称
     * @return 返回整数
     * @throws TemplateException 抛出模板异常
     */
    public static Integer getInt(String name, Map<String, TemplateModel> params)
            throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (Strings.isNullOrEmpty(s)) {
                return null;
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
            	throw Throwables.propagate(e);
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().intValue();
        } else {
        	throw new RuntimeException(name);
        }
    }

    /**
     * 取得整数数组
     *
     * @param name 名称
     * @return 返回整数数组
     * @throws TemplateException 抛出模板异常
     */
    public static Integer[] getIntArray(String name,
                                        Map<String, TemplateModel> params) throws TemplateException {
        String str = DirectiveUtils.getString(name, params);
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        String[] arr = StringUtils.split(str, ",");
        Integer[] ids = new Integer[arr.length];
        int i = 0;
        try {
            for (String s : arr) {
                ids[i++] = Integer.valueOf(s);
            }
            return ids;
        } catch (NumberFormatException e) {
        	throw Throwables.propagate(e);
        }
    }

    /**
     * 取得布尔值
     *
     * @param name 名称
     * @return 返回布尔值
     * @throws TemplateException 抛出模板异常
     */
    public static Boolean getBool(String name, Map<String, TemplateModel> params)
            throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) model).getAsBoolean();
        } else if (model instanceof TemplateNumberModel) {
            return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
        } else if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (Strings.isNullOrEmpty(s)) {
                return null;
            }
            return !s.equals("0");
        } else {
        	throw new RuntimeException(name);
        }
    }
}