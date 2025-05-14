package br.com.conversor.aplication;

import br.com.conversor.service.MenuService;

public class ConversorApplication {
	private final MenuService menuService;

	public ConversorApplication() {
		this.menuService = new MenuService();
	}
	
	public void iniciar() {
		menuService.exibirMenuPrincipal();
	}
}
