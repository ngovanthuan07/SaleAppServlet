package com.quanlybanhang.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DeCodeUtil {
	private static DeCodeUtil decode = null;
	
	public static DeCodeUtil getInstance() {
		if(decode == null)
		{
			decode = new DeCodeUtil();
		}
		return decode;
	}
	
    public  String decode(String url)  
    {  
              try {  
                   String prevURL="";  
                   String decodeURL = url;  
                   while(!prevURL.equals(decodeURL))  
                   {  
                        prevURL = decodeURL;  
                        decodeURL = URLDecoder.decode( decodeURL, "UTF-8" );  
                   }  
                   return decodeURL;  
              } catch (UnsupportedEncodingException e) {  
                   return null;  
              }  
    }  
}
