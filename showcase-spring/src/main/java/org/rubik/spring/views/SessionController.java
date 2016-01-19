package org.rubik.spring.views;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;


/**
 * session测试类.
 * 
 * @author xiajinxin
 */
@Controller
@RequestMapping(value = "/session")
public class SessionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);
	private static final String USER_KEY = "USER_SESSION_ID";

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public String findUserById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(USER_KEY, String.format("Hello, %s", "xiajinxin"));
		LOGGER.debug("Session设置成功");
		return "/user/userForm";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String) session.getAttribute(USER_KEY);
		LOGGER.debug("Session值:{}", value);
		return "/user/userList";
	}

	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));

        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", "this is Exception.class");

        return model;
    }
}
