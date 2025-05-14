package br.com.conversor.consumindoapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.conversor.model.RespostaCambioTaxa;
import br.com.conversor.model.RespostaConversao;

public class ConsumindoConversor {
	
	private static final String API_KEY = loadApiKeyFromEnv();
	private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;

	private static final Gson gson = new Gson();
	
	private static String loadApiKeyFromEnv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("API_KEY=")) {
                    return line.substring(8).trim();
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler API_KEY do arquivo .env: " + e.getMessage());
        }
        throw new IllegalStateException("API_KEY não encontrada no arquivo .env");
    }
	
	public JsonObject getSupportedCurrencies() throws Exception {
		String url = BASE_URL + "/codes";
		String json = fazerRequisicao(url);
		JsonObject response = gson.fromJson(json, JsonObject.class);
		
		if(!response.get("result").getAsString().equals("success")) {
			throw new Exception("Erro ao buscar moedas disponpiveis" + response);
		}
		
		if(!response.has("supported_codes")) {
			throw new Exception("Lamento, mas a API não contém 'supported_codes'");
		}
		
		return response;
	}

	public static RespostaCambioTaxa buscarBaseCambio(String base) throws Exception {
		String url = BASE_URL + "/latest/" + base;
		String json = fazerRequisicao(url);
		return gson.fromJson(json, RespostaCambioTaxa.class);
	}

	public static RespostaConversao convertToUsd(String base, double valor) throws Exception {
		String url = BASE_URL + "/pair/" + base + "/USD/" + valor;
		String json = fazerRequisicao(url);
		return gson.fromJson(json, RespostaConversao.class);
	}
	
	public static RespostaConversao convertToEur(String base, double valor) throws Exception{
		String url = BASE_URL + "/pair/" + base + "/EUR/" + valor;
		String json = fazerRequisicao(url);
		return gson.fromJson(json, RespostaConversao.class);
	}
	
	public static RespostaConversao convertToJpy(String base, double valor) throws Exception{
		String url = BASE_URL + "/pair/" + base + "/JPY/" + valor;
		String json = fazerRequisicao(url);
		return gson.fromJson(json, RespostaConversao.class);
	}
	
	public static RespostaConversao convertToCurrency(String base1, String base2, double valor) throws Exception{
		String url = BASE_URL + "/pair/" + base1 + "/" + base2 + "/" + valor;
		String json = fazerRequisicao(url);
		return gson.fromJson(json, RespostaConversao.class);
	}

	private static String fazerRequisicao(String url) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		// tem que ser o retorno do corpo da resposta, retorna o JSON e nao a URL
		return response.body();
	}
	
	private static String lerMoeda(Scanner scan, String mensagem) {
		System.out.println(mensagem);
		return scan.nextLine().toUpperCase();
	}
	
	private static double lerValor(Scanner scan, String mensagem) {
		System.out.println(mensagem);
		double valor = scan.nextDouble();
		scan.nextLine();
		return valor;
	}

}
