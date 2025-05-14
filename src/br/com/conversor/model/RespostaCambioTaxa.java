package br.com.conversor.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class RespostaCambioTaxa {
	
	private String result;
	@SerializedName("base_code")
	private String baseCode;
	@SerializedName("conversion_rates")
	private JsonObject conversionRates;
	
	
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
	public JsonObject getConversionRates() {
		return conversionRates;
	}
	public void setConversionRates(JsonObject conversionRate) {
		this.conversionRates = conversionRate;
	}
	
	

}

