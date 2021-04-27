package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import java.io.Serializable;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

public class AnaliseClienteResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	private Long idProposta;
	private String nome;
	
	// dependencia para o status da proposta
	
	private StatusPropostaClienteEnum statusProposta;
	
	// construtor default
	
	@Deprecated
	public AnaliseClienteResponse() {
		
	}
	
	// construtor com argumentos
	
	public AnaliseClienteResponse(Proposta entity) {
		this.idProposta = entity.getId();
		this.nome = entity.getNome();
		this.statusProposta = entity.getStatusProposta();
	}


	// getters

	
	public Long getIdProposta() {
		return idProposta;
	}

	public StatusPropostaClienteEnum getStatusProposta() {
		return statusProposta;
	}

	public String getNome() {
		return nome;
	}

}
