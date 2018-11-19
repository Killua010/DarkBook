package br.com.darkbook.util;

import java.io.BufferedReader;
import java.io.IOException;

public class JSONUtil {
	public static String ParseString(BufferedReader json) throws IOException{
		String JSON = "";
		String linha;
		while( (linha = json.readLine()) != null ){
			JSON += linha.trim().toString();
		}
        return JSON;
	}

}
