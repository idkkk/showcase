package org.rubik.account.web;

import org.rubik.account.domain.Account;
import org.rubik.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	public Account sync() {
		LOGGER.debug("执行sync方法...");
		return accountService.findById();
	}

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public DeferredResult<Account> javaAsync() {
		LOGGER.debug("执行async方法...");
		DeferredResult<Account> deferredResult = new DeferredResult<>();
		accountService.findByIdAsync().whenComplete((result, error) -> {
			if (error != null) deferredResult.setErrorResult(error);
			deferredResult.setResult(result);
		});
		return deferredResult;
	}
}
