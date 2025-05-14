package br.com.conversor.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.conversor.consumindoapi.ConsumindoConversor;
import br.com.conversor.exception.MoedaInvalidaException;
import br.com.conversor.model.RespostaCambioTaxa;
import br.com.conversor.model.RespostaConversao;

public class ConversorService {
	private final ConsumindoConversor apiClient;
	private List<String> moedasValidas;
	

	public ConversorService() {
		this.apiClient = new ConsumindoConversor();
		this.moedasValidas = carregarMoedasValidas();
	
		if(this.moedasValidas.isEmpty()) {
			System.err.println("AVISO. Usando lista carregada de moedas");
		}else {
			System.out.println("Moedas disponíveis carregadas com sucesso");
		}
	}
	
	public List<String> carregarMoedasValidas(){
		try {
			JsonObject response = apiClient.getSupportedCurrencies();
			JsonArray supportedCodes = response.getAsJsonArray("supported_codes");
			List<String> moedas = new ArrayList<>();
			
			for(JsonElement element : supportedCodes) {
				JsonArray currencyPair = element.getAsJsonArray();
				moedas.add(currencyPair.get(0).getAsString());
			}
			
			return moedas;
		}catch(Exception e) {
			System.err.println("Erro ao carregar moedas válidas: " + e.getMessage());
			
			return Arrays.asList("USD", "EUR", "GBP", "JPY", "AUD", "CHF", "CAD",
					"CNY", "ARS", "TRY", "BRL", "CHF");
		}
	}
	
	public void validarMoeda(String moeda) throws MoedaInvalidaException {
		if(!moedasValidas.contains(moeda)) {
			throw new MoedaInvalidaException("Moeda '" + moeda + "' não foi encontrada.\n" +
					"Moedas disponíveis: " + String.join(", ", moedasValidas), moedasValidas);
			
		}
	}
	
	public RespostaCambioTaxa buscartaxas(String moedaBase) throws Exception{
		return apiClient.buscarBaseCambio(moedaBase);
	}
	
	public RespostaConversao converter(String origem, String destino, double valor) throws Exception{
		if(valor <= 0) throw new IllegalArgumentException("Ocorreu um erro. Valor deve ser positivo!");
		
		validarMoeda(origem);
		validarMoeda(destino);
		
		RespostaConversao resultado = switch(destino){
		case "USD" -> apiClient.convertToUsd(origem, valor);
		case "EUR" -> apiClient.convertToEur(origem, valor);
		case "JPY" -> apiClient.convertToJpy(origem, valor);
		default -> apiClient.convertToCurrency(origem, destino, valor);
		};
		
		if(resultado.getConversionRate() <= 0 || Double.isNaN(resultado.getConversionRate())) {
			throw new MoedaInvalidaException("Não foi possível converter - moeda(s) inválida(s)", moedasValidas);
		}
		return resultado;
	}
}
