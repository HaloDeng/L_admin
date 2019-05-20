package com.halo.admin.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Md5Util {



	/**
	 * @param str 待加密源串
	 * @return 加密后的字符串
	 */
	public static String encode(String str) {
		if (str == null) {
			return "";
		}
		MessageDigest messageDigest;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			log.error("md5加密失败！", e);
			return str;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuilder md5StrBuff = new StringBuilder();
		for (byte aByteArray : byteArray) {
			if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
			}
		}
		return md5StrBuff.toString();
	}
}
