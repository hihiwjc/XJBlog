package cn.hihiwjc.app.xjblog.com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

	private KeyGenerator() {

	}
	
	public static String generateMD5(String key) {
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			byte[] bytes = mDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (byte aByte : bytes) {
				String hex = Integer.toHexString(0xFF & aByte);
				if (hex.length() == 1) {
					sb.append('0');
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return String.valueOf(key.hashCode());
		}
	}

}
