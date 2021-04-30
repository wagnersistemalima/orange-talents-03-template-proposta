package br.com.zupacademy.wagner.proposta.associaCartaoProposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.sun.istack.NotNull;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

// objeto que carrega dados de resposta da api externa, dados do cartao aprovado para a proposta

public class PropostaCartaoResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atribiutos basicos
	
	@NotBlank
	private String id;                                      // id gerado pela api externa
	
	@NotBlank
	private String emitidoEm;
	
	@NotBlank
	private String titular;
	
	@Positive
	@NotNull
	private Integer limite;
	
	@NotBlank
	private String idProposta;
	
	// construtor com argumentos
	
	@JsonCreator
	public PropostaCartaoResponse(@NotBlank String id, @NotBlank String emitidoEm, @NotBlank String titular,
			@Positive Integer limite, @NotBlank String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}
	
	// getters

	public String getId() {
		return id;
	}

	

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Integer getLimite() {
		return limite;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	// metodo para converter o response em entidade

	public Cartao toModel(Proposta retornoProposta) {
		
		return new Cartao(id, emitidoEm, titular, new BigDecimal(limite), retornoProposta);
	}
	

}
