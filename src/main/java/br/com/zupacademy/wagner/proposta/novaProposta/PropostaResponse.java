package br.com.zupacademy.wagner.proposta.novaProposta;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.StatusPropostaClienteEnum;

// objeto para responder dados

public class PropostaResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	private Long id;
	
	private StatusPropostaClienteEnum statusProposta;
	

	@JsonCreator
	public PropostaResponse(Proposta entity) {
		this.id = entity.getId();
		
		this.statusProposta = entity.getStatusProposta();
		
	}

	
	public StatusPropostaClienteEnum getStatusProposta() {
		return statusProposta;
	}


	public Long getId() {
		return id;
	}
	

}
