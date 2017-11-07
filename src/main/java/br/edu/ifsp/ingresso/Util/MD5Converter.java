package br.edu.ifsp.ingresso.Util;

import java.security.*;
import java.math.*;

public class MD5Converter {
	
	    public static String convertToMd5(String str) {
	    	
	       MessageDigest m = null;
	       
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	       m.update(str.getBytes(),0,str.length());
	       return new BigInteger(1,m.digest()).toString();
	    }
	

}
