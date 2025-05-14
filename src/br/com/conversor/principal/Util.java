package br.com.conversor.principal;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.conversor.consumindoapi.ConsumindoConversor;

public class Util {

	public static double obterConversaoTaxaJsonToString(String jsonResponse) {
		
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		
		if(jsonObject.has("conversion_rate")) {
			return jsonObject.get("conversion_rate").getAsDouble();
		}
		
		if(jsonObject.has(("conversion_result"))){
			return jsonObject.get("conversion_result").getAsDouble();
		}
		
		throw new RuntimeException("Campo 'conversion_rate' não foi localizado no JSON");
		
	}

	public static void imprimirDetalhesJson(String jsonResponse) {
		JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
		
		System.out.println("Detalhes da resposta:");
		if(jsonObject.has("result")) {
			System.out.println("Resultado: " + jsonObject.get("result").getAsString());
		}
		if(jsonObject.has("conversion_rate")) {
			System.out.println("Taxa de conversão: " + jsonObject.get("conversion_rate").getAsDouble());
		}
		if(jsonObject.has("conversion_result")) {
			System.out.println("Valor convertido: " + jsonObject.get("conversion_result").getAsDouble());
		}
		if(jsonObject.has("time_last_update_utc")) {
			System.out.println("Última atualiação: " + jsonObject.get("time_last_update_utc").getAsString());
		}
		
	}
}
