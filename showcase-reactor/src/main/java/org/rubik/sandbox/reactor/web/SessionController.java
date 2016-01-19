package org.rubik.sandbox.reactor.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * session测试类.
 * 
 * @author xiajinxin
 */
@RestController
@RequestMapping(value = "/session")
public class SessionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);
	private static final String USER_KEY = "USER_SESSION_ID";

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public ModelMap findUserById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(USER_KEY, String.format("Hello, %s", "xiajinxin"));
		LOGGER.debug("Session设置成功");
		ModelMap result = new ModelMap();
		result.addAttribute("code", "0");
		result.addAttribute("message", "操作成功！");
		return result;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelMap list(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String value = (String) session.getAttribute(USER_KEY);
		LOGGER.debug("Session值:{}", value);
		ModelMap result = new ModelMap();
		result.addAttribute("code", "0");
		result.addAttribute("message", String.format("SESSION值:%s", value));
		return result;
	}

	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));

        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", "this is Exception.class");

        return model;
    }
}
