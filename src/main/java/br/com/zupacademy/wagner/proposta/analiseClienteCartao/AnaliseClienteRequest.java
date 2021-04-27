package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

// objetos para trafegar dados de consulta 

public class AnaliseClienteRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	// atributos basico
	
	@NotNull
	private Long IdProposta;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String nome;
	
	
	// construtor com argumentos
	
	@JsonCreator
	public AnaliseClienteRequest(@NotNull Long idProposta, @NotBlank String documento, @NotBlank String nome) {
		IdProposta = idProposta;
		this.documento = documento;
		this.nome = nome;
	}


	// getters


	public String getDocumento() {
		return documento;
	}


	public String getCliente() {
		return nome;
	}
	


	public Long getIdProposta() {
		return IdProposta;
	}

	public Proposta toModel(EntityManager manager) {
		
		Proposta novaProposta = manager.find(Proposta.class, this.IdProposta);
				
		return novaProposta;
	}


	
}
