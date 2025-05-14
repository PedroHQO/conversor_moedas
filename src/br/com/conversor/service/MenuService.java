package br.com.conversor.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import br.com.conversor.consumindoapi.ConsumindoConversor;
import br.com.conversor.exception.MoedaInvalidaException;
import br.com.conversor.model.RespostaCambioTaxa;
import br.com.conversor.model.RespostaConversao;
import br.com.conversor.util.InputHandler;

public class MenuService {
	private final InputHandler input;
	private final ConversorService conversor;
	private final List<String> MOEDAS_DISPONIVEIS = Arrays.asList("USD", "EUR", "GBP", "JPY", "AUD", "CHF", "CAD",
			"CNY", "ARS", "TRY", "BRL", "CHF");

	public MenuService() {
		this.input = new InputHandler();
		this.conversor = new ConversorService();
	}

	public void exibirMenuPrincipal() {
		int opcao;

		do {
			exibirOpcoes();
			opcao = input.lerInt("Escolha uma opção: ");
			processarOpcao(opcao);
		} while (opcao != 6);
	}

	public void exibirOpcoes() {
		String menu = """
				++++++++++++++++++++++++++++++++++++
				1 - Mostrar lista de câmbios
				2 - Converter para USD(dolar)
				3 - Converter para EUR(euro)
				4 - Converter para JPY(iene)
				5 - Converter Moeda Personalizada
				6 - Sair
				++++++++++++++++++++++++++++++++++++
				""";
		System.out.println(menu);
	}

	private void processarOpcao(int opcao) {
		try {
			switch (opcao) {
			case 1 -> mostrarTaxaCambio();
			case 2 -> converterMoeda("USD");
			case 3 -> converterMoeda("EUR");
			case 4 -> converterMoeda("JPY");
			case 5 -> converterMoedaPersonalizada();
			case 6 -> System.out.println("SAINDO...");
			default -> System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.out.println("Falha ao exibir menu. Tente mais tarde!");
		}

	}

	private void mostrarTaxaCambio() throws Exception {
		String base = input.lerString("Informe a moeda base para ver as taxas (ex: EUR, USD, BRL...): ");

		RespostaCambioTaxa respostaTaxa = ConsumindoConversor.buscarBaseCambio(base);

		JsonObject taxa = respostaTaxa.getConversionRates();
		if (taxa == null) {
			System.out.println("Não foi possível obter as taxas de câmbio");
			return;
		}

		System.out.println("\nTaxas de câmbio para " + base + ":");
		MOEDAS_DISPONIVEIS.forEach(moeda -> {
			JsonElement elemento = taxa.get(moeda);
			System.out.println(moeda + ": " + (elemento != null ? elemento.getAsDouble() : "Não disponível"));
		});
	}

	private void converterMoeda(String moedaDestino) throws Exception {
		try {
			String origem = input.lerString("Digite a moeda origem (BRL, USD): ");
			double valor = input.lerDouble("Valor a ser convertido: ");

			RespostaConversao resultado = conversor.converter(origem, moedaDestino, valor);
			
			resultado.validar();
			exibirResultado(resultado);
			
		}catch(MoedaInvalidaException e) {
			System.out.println("\nErro: " + e.getMessage());
			if(!e.getMoedasDisponiveis().isEmpty()) {
				System.out.println("Moedas disponíveis: " + e.getMoedasDisponiveis()
				.stream()
				.limit(10)
				.collect(Collectors.joining(", ")));
			}
			
			System.out.println("Por favor, tente novamente!");
		}catch(Exception e) {
			System.out.println("\nErro na conversão: " + e.getMessage() + "\n");
		}
	}
	
	private void converterMoedaPersonalizada() throws Exception {
		try {
			String origem = input.lerString("Digite a moeda origem (BRL, USD): ");
			String destino = input.lerString("Digite a moeda destino: ");
			double valor = input.lerDouble("Valor a ser convertido: ");
			
			RespostaConversao resultado = conversor.converter(origem, destino, valor);
			exibirResultado(resultado);
		}catch(MoedaInvalidaException e) {
			System.out.println("\nErro: " + e.getMessage());
			System.out.println("Moedas disponíveis: " + e.getMoedasDisponiveis()
			.stream()
			.limit(10)
			.collect(Collectors.joining(", ")));
			System.out.println("Por favor, tente novamente!");
		}catch(Exception e) {
			System.out.println("\nErro na conversão: " + e.getMessage() + "\n");
		}
	}
	
	private void exibirResultado(RespostaConversao resultado) {
		System.out.println("\nResultado: ");
		System.out.println(resultado);
	}
}
