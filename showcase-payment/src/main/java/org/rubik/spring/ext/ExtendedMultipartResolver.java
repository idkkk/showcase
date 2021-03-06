package org.rubik.spring.ext;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class ExtendedMultipartResolver  extends CommonsMultipartResolver {
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        if(request!=null) {
            String contentType = request.getContentType();
            return (contentType != null && contentType.toLowerCase().startsWith("multipart"));
        }else {
            return false;
        }
    }
}