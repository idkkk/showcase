package org.rubik.order.client;

import org.rubik.order.domain.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-service")
public interface AccountClient {

	@RequestMapping(value = "/account/sync", method = RequestMethod.GET)
	public Account getUser();

	@RequestMapping(value = "/account/async", method = RequestMethod.GET)
	public Account getUserAsync();
}

//@Component
//public class AccountClient {
//
//	@Autowired
//	private RestTemplate restTemplate;
//
//	@HystrixCommand(fallbackMethod = "getUserFallback")
//	public Account getUser() {
//		return restTemplate.getForObject("http://account-service/account/sync", Account.class);
//	}
//
//	public Account getUserFallback() {
//		Account dummy = new Account();
//		dummy.setId(999L);
//		dummy.setLevel(999);
//		dummy.setName("default");
//		dummy.setNickName("nickeName");
//		return new Account();
//	}
//
//	public Account getUserAsync() {
//		return restTemplate.getForObject("http://account-service/account/async", Account.class);
//	}
//}