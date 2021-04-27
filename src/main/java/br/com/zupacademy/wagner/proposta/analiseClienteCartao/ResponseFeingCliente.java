package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ResponseFeingCliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	private String idProposta;
	
	private String nome;
	
	private String resultadoSolicitacao;
	
	@JsonCreator
	public ResponseFeingCliente(String idProposta, String nome, String resultadoSolicitacao) {
		this.idProposta = idProposta;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	// getters

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}
	
	
}
