package org.rubik.datasource.views;

import java.util.List;

import org.rubik.datasource.domain.User;
import org.rubik.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{age}", method = RequestMethod.GET)
	@ResponseBody
	public User findUserById(@PathVariable("age") int age) {
		User user = userService.findByAge(age);
		if (user != null) {
			return user;
		} else {
			throw new RuntimeException("empty user");
		}

//		Optional<User> user = Optional.of(userService.findByName(name));
//		if (user.isPresent()) {
//			return user.get();
//		} else {
//			throw new RuntimeException("empty user");
//		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		List<User> users = userService.findAll();
		return users;
	}

//	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//	public User delete(@PathVariable("id") Integer id) {
//		Product product = new Product();
//		product.setId(id);
//		product.setName("MyWifi");
//		product.setInventory(1000000);
//		return product;
//	}
}
