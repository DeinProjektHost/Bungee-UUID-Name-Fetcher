package me.veriox.unf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class BungeeNameFetcher {
	
	public static String getName(String uuid) {
		String output = callURL("https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names");
		
		StringBuilder result = new StringBuilder();
		
		int i = output.length()-1;
		
		while(i > -1000) {
			
			if((String.valueOf(output.charAt(i)).equalsIgnoreCase("e")) && (String.valueOf(output.charAt(i-1)).equalsIgnoreCase("m")) && (String.valueOf(output.charAt(i-2)).equalsIgnoreCase("a")) && (String.valueOf(output.charAt(i-3)).equalsIgnoreCase("n"))) {
				
				int k = i+4;
				
				while(k < 1000) {
					
					if(!String.valueOf(output.charAt(k)).equalsIgnoreCase("\"")) {
						
						result.append(String.valueOf(output.charAt(k)));
						
					} else {
						break;
					}
					
					k++;
				}
				
				break;
			}
			
			i--;
		}
		
		return result.toString();
	}
	
	private static String callURL(String URL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(URL);
			urlConn = url.openConnection();
			
			if (urlConn != null) urlConn.setReadTimeout(60 * 1000);
			
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				
				if (bufferedReader != null) {
					int cp;
					
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					
					bufferedReader.close();
				}
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
 
		return sb.toString();
	}
}