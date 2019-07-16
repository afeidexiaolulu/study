package com.atguigu.atcrowdfunding.util;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法 哈希算法 MD5算法具有以下特点： 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的。 2、容易计算：从原数据计算出MD5值很容易。
 * 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
 * 4、强抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
 */
//加密
public class MD5Util {
	public static String digest16(String inStr) {
		return digest(inStr, 16); 
	}

	public static String digest(String inStr) {
		return digest(inStr, 32);
	}

	//密码加密 循环7次
	public static String digestPassWord(String passWord){

		String digestPassWord = passWord;
		for(int i=0; i<7; i++){
			digestPassWord= digest(digestPassWord);
		}
		return digestPassWord;
	}


	//加密为32小
	private static String digest(String inStr, int rang) {
		MessageDigest md5 = null;
		if (StringUtils.isEmpty(inStr)) {
			return "";
		}

		try {
			md5 = MessageDigest.getInstance("MD5"); // 取得算法
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray); // 加密

		StringBuilder hexValue = new StringBuilder();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		if (rang == 32) {
			return hexValue.toString();
		} else {
			return hexValue.toString().substring(8, 24);// 转换为32位字符串
		}
	}


	//md5加密 32位小
	public static String toMD5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String args[]) {
		String s = new String("123");
		System.out.println(digestPassWord(s));
		System.out.println(digest(s));
		System.out.println(toMD5(s));
	}
}