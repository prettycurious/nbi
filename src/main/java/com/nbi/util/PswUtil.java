package com.nbi.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PswUtil {

	public static String MD5(String password, String name) {

		// MD5盐值加密
		String hashAlgorithmName = "MD5";
		Object credentials = password;
		Object salt = ByteSource.Util.bytes(name);
		int hashIterations = 1024;
		String resultPassword = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
		return resultPassword;
	}

}
