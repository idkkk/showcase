package org.rubik.spring.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Component
public class PaymentUtils {

	private PaymentUtils() {
	}

	public static String signMsg(String signMsg) {
		String base64 = "";
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream ksfis = new FileInputStream(ResourceUtils.getFile("classpath:tester-rsa.pfx"));
			BufferedInputStream ksbufin = new BufferedInputStream(ksfis);

			char[] keyPwd = "123456".toCharArray();
			ks.load(ksbufin, keyPwd);
			PrivateKey priK = (PrivateKey) ks.getKey("test-alias", keyPwd);
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(priK);
			signature.update(signMsg.getBytes("utf-8"));
			BASE64Encoder encoder = new BASE64Encoder();
			base64 = encoder.encode(signature.sign());
		} catch (FileNotFoundException e) {
			// TODO
		} catch (Exception ex) {
			// TODO
		}
		return base64;
	}

	public static boolean enCodeByCer(String val, String msg) {
		boolean flag = false;
		try {
			System.out.println(ResourceUtils.getFile("classpath:99bill[1].cert.rsa.20140803.cer").getName());
			FileInputStream inStream = new FileInputStream(ResourceUtils.getFile("classpath:99bill[1].cert.rsa.20140803.cer"));

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf
			        .generateCertificate(inStream);
			PublicKey pk = cert.getPublicKey();
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pk);
			signature.update(val.getBytes());
			BASE64Decoder decoder = new BASE64Decoder();
			flag = signature.verify(decoder.decodeBuffer(msg));
		} catch (Exception e) {
			// TODO
		}
		return flag;
	}
}
