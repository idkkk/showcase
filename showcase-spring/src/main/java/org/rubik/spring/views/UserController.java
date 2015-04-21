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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String findUserById(@PathVariable("id") Long id, ModelMap model) {
		LOGGER.debug("Parameter[Id]: {}", id);
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

//	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//	public User delete(@PathVariable("id") Integer id) {
//		Product product = new Product();
//		product.setId(id);
//		product.setName("MyWifi");
//		product.setInventory(1000000);
//		return product;
//	}

	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));

        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", "this is Exception.class");

        return model;
    }
}
