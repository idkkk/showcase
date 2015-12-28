package org.rubik.sandbox.ik;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzerTest {

//	private static final String SHARED_CONENT = "2016年欧洲杯四强赛";
	private static final String SHARED_CONENT = "最希望从企业得到的是独家的内容或销售信息，获得打折或促销信息等；最不希望企业进行消息或广告轰炸及访问用户的个人信息等等。这从而值从而得使用社会化媒体的企业研究";
	
	public static void main(String[] args) throws Exception {
		IKAnalyzer analyzer = new IKAnalyzer(true);

		// 获取Lucene的TokenStream对象
		TokenStream ts = null;
		try {
			ts = analyzer.tokenStream("message", new StringReader(SHARED_CONENT));
			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			ts.reset();
			while (ts.incrementToken()) {
				System.out.println(term.toString());
			}
			ts.end();
		} catch (IOException e) {
			//
		} finally {
			// 释放TokenStream的所有资源
			if (ts != null) {
				try {
					ts.close();
				} catch (IOException e) {
					//
				}
			}
		}
	}
}
