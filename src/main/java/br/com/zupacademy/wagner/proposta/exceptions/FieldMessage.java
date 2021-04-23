package br.com.zupacademy.wagner.proposta.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos basicos

	private String fieldName;
	private String message;
	
	// construtor default
	
	@Deprecated
	public FieldMessage() {
		
	}
	
	// construtor com argumentos

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	
	// getters

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}
	
	

}
