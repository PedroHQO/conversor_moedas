package br.com.conversor.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import br.com.conversor.exception.MoedaInvalidaException;

public class RespostaConversao {
	
	private String result;
	@SerializedName("base_code")
	private String baseCode;
	@SerializedName("target_code")
	private String targetCode;
	@SerializedName("conversion_rate")
	private double conversionRate;
	@SerializedName("conversion_result")
	private double conversionResult;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	

	public double getConversionResult() {
		return conversionResult;
	}

	public void setConversionResult(double conversionResult) {
		this.conversionResult = conversionResult;
	}

	public void validar() throws MoedaInvalidaException {
		if(Double.isNaN(conversionRate) || Double.isNaN(conversionResult)) {
			throw new MoedaInvalidaException("Conversão inválida - moeda não suportada", 
					List.of());
		}
	}
	
	@Override
	public String toString() {
		return String.format("Conversão: %.2f %s = %.2f %s (Taxa: %.3f)", 
				conversionResult / conversionRate,
				baseCode, 
				conversionResult, 
				targetCode, 
				conversionRate);
	}
	

}
