package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResponseFeingCliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	private String resultadoSolicitacao;
	
	
	@JsonCreator
	public ResponseFeingCliente(String resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}


	// getters

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	
	
}
