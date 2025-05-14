package br.com.conversor.principal;

import br.com.conversor.aplication.ConversorApplication;

public class Main {

	public static void main(String[] args) {
		try {
			new ConversorApplication().iniciar();
		}catch(Exception e){
			System.err.println("Erro fatal: " + e.getMessage());
		}
	}

}
