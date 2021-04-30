package br.com.zupacademy.wagner.proposta.associaCartaoProposta;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

// objeto para trafegar dados, solicitação de cartao a proposta aprovada

public class PropostaSolicitaCartaoRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	private String documento;
	
	private String nome;
	
	private String idproposta;
	
	// construtor com argumentos
	
	@JsonCreator
	public PropostaSolicitaCartaoRequest(String documento, String nome, String idproposta) {
		this.documento = documento;
		this.nome = nome;
		this.idproposta = idproposta;
	}
	
	// getters

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdproposta() {
		return idproposta;
	}
	
	

}
