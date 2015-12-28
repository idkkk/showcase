/**
 * Copyright ©2008-2012 Lafaso.com All Rights Reserved. 
 */
package org.rubik.spring.freemarker;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import org.rubik.spring.util.DirectiveUtils;
import org.springframework.util.Assert;

import com.google.common.base.Throwables;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.utility.StringUtil;

/**
 * <p>
 * 字符串截取。 <br />
 * 如果截取长度为2，则英文截取4个，中文截取2个。
 * </p>
 * 
 * <p>@author xiajinxin</p>
 * <p>@since 2012-12-10</p>
 */
public class StringStripDirective implements TemplateDirectiveModel {

    private static final String TEXT = "text";      // 需要进行截取处理的字符串
    private static final String LENGTH = "length";  // 默认截取长度，默认为10
    private static final String EXT = "ext";        // 截取后追加的字符串，比如：...

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void execute(Environment env, Map params,
            TemplateModel[] templateModels, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String text = DirectiveUtils.getString(TEXT, params);
        int length = DirectiveUtils.getInt(LENGTH, params);
        String ext = DirectiveUtils.getString(EXT, params);

        Assert.notNull(text);
        if (body != null) {
            Writer out = env.getOut();
            out.append(subString(text, length, ext));
            body.render(out);
        } else {
            Writer out = env.getOut();
            out.append(subString(text, length, ext));
        }
    }

    // 字符串截取处理
    private static String subString(String text, int length, String ext) {
        int textLength = text.length();
        int byteLength = 0;
        StringBuilder returnStr = new StringBuilder();
        for (int i = 0; i < textLength && byteLength < length * 2; i++) {
            String tempStr = text.substring(i, i + 1);
            if (tempStr.getBytes().length == 1) {// 英文
                byteLength++;
            } else {// 中文
                byteLength += 2;
            }
            returnStr.append(tempStr);
        }
        try {
            if (byteLength < text.getBytes("GBK").length) {
                returnStr.append(ext);
            }
        } catch (UnsupportedEncodingException e) {
        	throw Throwables.propagate(e);
        }
        return StringUtil.XHTMLEnc(returnStr.toString());  // 调用freemarker自身类做html转义处理
    }
}