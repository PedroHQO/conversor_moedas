package br.com.conversor.exception;

import java.util.Collection;
import java.util.List;

public class MoedaInvalidaException extends Exception{
	private final List<String> moedasDisponiveis;
	
	public MoedaInvalidaException(String message, List<String> moedasDisponiveis) {
		super(message);
		this.moedasDisponiveis = moedasDisponiveis;
	}
	
	public List<String> getMoedasDisponiveis(){
		return moedasDisponiveis;
	}

}
