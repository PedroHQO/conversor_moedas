package br.com.conversor.util;

import java.util.Scanner;

public class InputHandler {

	private final Scanner scanner;

	public InputHandler() {
		this.scanner = new Scanner(System.in);
	}

	public String lerString(String mensagem) {
		System.out.println(mensagem);
		return scanner.nextLine().trim().toUpperCase();
	}

	public int lerInt(String mensagem) {
		while (true) {
			try {
				System.out.println(mensagem);
				int valor = Integer.parseInt(scanner.nextLine());
				return valor;
			} catch (NumberFormatException e) {
				System.out.println("Por favor, digite um número inteiro válido.");
			}
		}
	}

	public double lerDouble(String mensagem) {
		while (true) {
			try {
				System.out.println(mensagem);
				return Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Por favor, digite um número inteiro válido.");
			}
		}
	}

	public void close() {
		scanner.close();
	}
}
