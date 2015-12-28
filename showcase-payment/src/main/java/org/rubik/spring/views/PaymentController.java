package org.rubik.spring.views;

import java.util.Map;

import org.rubik.spring.domain.PaymentResult;
import org.rubik.spring.util.PaymentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;


/**
 * 快钱支付.
 * 
 * @author xiajinxin
 */

@Controller
public class PaymentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Value("${payment.bgUrl}")
	private String bgUrl;

	@Value("${payment.merchantAcctId}")
	private String merchantAcctId;

	@RequestMapping(value = "/payment/send", method = RequestMethod.GET)
	public String send(ModelMap model) {
		Map<String, String> data = Maps.newLinkedHashMap();
		data.put("inputCharset", "1");
		data.put("pageUrl", "");
		data.put("bgUrl", bgUrl);                      //反馈页面
		data.put("version", "v2.0");
		data.put("language", "1");
		data.put("signType", "4");
		data.put("merchantAcctId", merchantAcctId);    // 商户号
		data.put("payerName", "xiajinxin");
		data.put("payerContactType", "1");
		data.put("payerContact", "2532987@qq.com");        // 邮件地址
		data.put("orderId", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));      // 订单号
		data.put("orderAmount", "1");    //订单金额
		data.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));    // 下单时间
		data.put("productName", "苹果");        // 商品名称
		data.put("productNum", "5");           // 商品数量
		data.put("productId", "55558888");     // 商品编号
		data.put("productDesc", "");           // 商品描述
		data.put("payType", "00");

		String params = Joiner.on("&").withKeyValueSeparator("=").join(Maps.filterValues(data, new Predicate<String>() {
            public boolean apply(String value) {
	            return !Strings.isNullOrEmpty(value);
            }
		}));
		String sign = PaymentUtils.signMsg(params);
		data.put("signMsg", sign);
		System.out.println("参数串: " + params);
		System.out.println("提交时的签名: " + sign);
		data.put("payGateway", "https://sandbox.99bill.com/gateway/recvMerchantInfoAction.htm");
		model.addAttribute("data", data);
		return "/payment/order";
	}

	@RequestMapping(value = "/payment/callback", method = RequestMethod.GET)
	public String callback(@ModelAttribute PaymentResult result) {
		Map<String, String> data = Maps.newLinkedHashMap();
        data.put("merchantAcctId", result.getMerchantAcctId());
        data.put("version", result.getVersion());
        data.put("language", result.getLanguage());
        data.put("signType", result.getSignType());
        data.put("payType", result.getPayType());
        data.put("bankId", result.getBankId());
        data.put("orderId", result.getOrderId());
        data.put("orderTime", result.getOrderTime());
        data.put("orderAmount", result.getOrderAmount());
        data.put("dealId", result.getDealId());
        data.put("bankDealId", result.getBankDealId());
        data.put("dealTime", result.getDealTime());
        data.put("payAmount", result.getPayAmount());
        data.put("fee", result.getFee());
        data.put("payResult", result.getPayResult());
        data.put("errCode", result.getErrCode());

        String params = Joiner.on("&").withKeyValueSeparator("=").join(Maps.filterValues(data, new Predicate<String>() {
            public boolean apply(String value) {
	            return !Strings.isNullOrEmpty(value);
            }
		}));
        boolean flag = PaymentUtils.enCodeByCer(params, result.getSignMsg());
        System.out.println("支付返回结果: " + flag);
		return "/payment/show";
	}

	@ExceptionHandler
	public String handleException(Exception ex) {
        LOGGER.error("Exception info:{}", Throwables.getStackTraceAsString(ex));

        // TODO

        return "error";
    }
}
