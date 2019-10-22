package com.kimreporter.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassCrypto {

	public static final String	CRYTO_ENCKEY		= "ZVIM~108~!STUDIO";

	public static String encode(String text) {
		String		generatedPassword = "";
		try {
			MessageDigest	md = MessageDigest.getInstance("SHA-256");
			byte[] 			salt = generateKey16(CRYTO_ENCKEY);
			byte[] 			bytes;

			md.update(salt);
			bytes = md.digest(text.getBytes());

			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++) {
			    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static boolean matches(String text, String encodedPassword) {
		if (text == null || encodedPassword == null)
			return false;
		if (text.length() == 0 || encodedPassword.length() == 0)
			return false;

		String	encPass = encode(text);
		
		return encodedPassword.equals(encPass);
	}

	private static final String		DEF_CHARSET		= "UTF-8";
	private static byte[]			s_cryptoKey		= null;

	private static byte[] generateKey16(String key) {
		if (s_cryptoKey != null)
			return s_cryptoKey;

		s_cryptoKey = new byte[16];

		byte[] 	byKey = null;

		try {
			byKey = key.getBytes(DEF_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (byKey.length < s_cryptoKey.length) {
			System.arraycopy(byKey, 0, s_cryptoKey, 0, byKey.length);

			for (int i = byKey.length; i < s_cryptoKey.length; i++)
				s_cryptoKey[i] = 0;
		}
		else {
			System.arraycopy(byKey, 0, s_cryptoKey, 0, s_cryptoKey.length);
		}

		return s_cryptoKey;
	}

}
