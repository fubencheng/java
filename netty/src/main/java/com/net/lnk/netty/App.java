package com.net.lnk.netty;

import com.luhuiguo.chinese.ChineseUtils;
import com.luhuiguo.chinese.pinyin.PinyinFormat;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println(ChineseUtils.toSimplified("張偉偉"));
		System.out.println(ChineseUtils.toTraditional("张伟伟"));
		System.out.println(ChineseUtils.toPinyin("张伟伟", PinyinFormat.TONELESS_PINYIN_FORMAT));
	}
}
