package org.rubik.account.service;

import java.util.concurrent.CompletableFuture;

import org.rubik.account.domain.Account;
import org.rubik.account.util.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 逻辑处理类.
 * 
 * @author xiajinxin
 *
 */
@Service
public class AccountService {

	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;
	
	// ======================================================  public method
	// sync
	public Account findById() {
		return dummyAccount();
	}

	// async
	public CompletableFuture<Account> findByIdAsync() {
		return CompletableFuture.supplyAsync(() -> {
			ThreadUtils.delay(1000);
			return dummyAccount();
		}, asyncTaskExecutor);
	}

	// ======================================================  private method
	private Account dummyAccount() {
		Account account = new Account();
		account.setId(100L);
		account.setName("smartisan");
		account.setNickName("测试用户");
		account.setLevel(0);

		return account;
	}
}
