package br.com.darkbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
	
	public static String ParseString(BufferedReader json) throws IOException{
		//Map<String,String> jsonMap = new HashMap<String,String>();
		String JSAO = "";
		String linha;
      //  try {
			while( (linha = json.readLine()) != null ){
//			    if(linha.trim().compareTo("{") != 0
//			    		&& linha.trim().compareTo("}") != 0){
//			    	
//			    	linha = linha.replace(',', ' ');
//			    	System.out.println(linha);
//			    	String[] jsons = linha.split(":");
//			    	jsonMap.put(jsons[0].replaceAll("\"", "").trim().toString(), jsons[1].replaceAll("\"", "").trim().toString());
				JSAO += linha.trim().toString();
			//    }// ./if
			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        return JSAO;
	}

}
