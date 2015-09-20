package me.veriox.unf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeUUIDFetcher {
	
	public static String getUUID(ProxiedPlayer player) {
		String output = callURL("https://api.mojang.com/users/profiles/minecraft/" + player.getName());
		
		StringBuilder result = new StringBuilder();
		
		for(int i = 7; i < 200; i++) {
			if(!String.valueOf(output.charAt(i)).equalsIgnoreCase("\"")) {
				
				result.append(String.valueOf(output.charAt(i)));
				
			} else {
				break;
			}
		}
		
		return result.toString();
	}
	
	public static String getUUID(String name) {
		String output = callURL("https://api.mojang.com/users/profiles/minecraft/" + name);
		
		StringBuilder result = new StringBuilder();
		
		for(int i = 7; i < 200; i++) {
			if(!String.valueOf(output.charAt(i)).equalsIgnoreCase("\"")) {
				
				result.append(String.valueOf(output.charAt(i)));
				
			} else {
				break;
			}
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