package org.rubik.spring.views;

import java.util.List;

import org.rubik.spring.domain.User;
import org.rubik.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;


/**
 * 以遵循 RFC-2616 所定义的协议的方式显式地使用 HTTP 方法，建立创建、检索、更新和删除（CRUD：Create, Retrieve, Update and Delete）操作与 HTTP 方法之间的一对一映射：
 * 若要在服务器上创建资源，应该使用 POST 方法；
 * 若要检索某个资源，应该使用 GET 方法；
 * 若要更改资源状态或对其进行更新，应该使用 PUT 方法；
 * 若要删除某个资源，应该使用 DELETE 方法。
 * 
 * @author xiajinxin
 */
@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String findUserById(@PathVariable("id") Long id, ModelMap model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "/user/userForm";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "/user/userList";
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String save(@RequestBody User user) {
		userService.save(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.PUT)
	public String update(@RequestBody User user) {
		userService.save(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/users";
	}

	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));

        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", "this is Exception.class");

        return model;
    }
}
