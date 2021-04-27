package br.com.zupacademy.wagner.proposta.novaProposta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseClienteRequest;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseFeingCliente;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.ResponseFeingCliente;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.StatusPropostaClienteEnum;

@Entity
public class Proposta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Column(unique = true)
	private String documento;
	
	@Email
	@NotBlank
	private String email;
	
	@Positive
	@NotNull
	private BigDecimal salario;
	
	//associação com endereco / chave estrangeira / CascadeType.All quando atualizadas as informações 
	// da Entidade Proposta, também será atualizado no banco de dados todas as informações da 
	// Entidades endereco associada.
	
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private StatusPropostaClienteEnum statusProposta = StatusPropostaClienteEnum.NAO_ELEGIVEL;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataRegistro;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateDataRegistro;

	
	// construtor default
	
	@Deprecated
	public Proposta() {
		
	}
	
	// construtor com argumentos
	
	public Proposta(@NotBlank String nome, @NotBlank String documento, @Email @NotBlank String email,
			@Positive @NotNull BigDecimal salario, @NotNull @Valid Endereco endereco) {
		this.nome = nome;
		this.documento = documento;
		this.email = email;
		this.salario = salario;
		this.endereco = endereco;
	}

	// getters

	public String getDocumento() {
		return documento;
	}
	
	

	public Instant getUpdateDataRegistro() {
		return updateDataRegistro;
	}

	public String getNome() {
		return nome;
	}

	public StatusPropostaClienteEnum getStatusProposta() {
		return statusProposta;
	}

	public String getEmail() {
		return email;
	}


	public BigDecimal getSalario() {
		return salario;
	}

	public Long getId() {
		return id;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	// hashCode $ equals comparando pelo documento
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposta other = (Proposta) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}
	
	// metodo para auxiliar para sempre que for salvar uma proposta, armazenar na dataRegistro o instante atual

	@PrePersist
	public void prePersist() {
		dataRegistro = Instant.now();
	}
	
	// metodo para auxiliar para sempre que for atualizar uma proposta, armazenar na updateDataRegistro o instante atual
	

	@PreUpdate
	public void preUpdate() {
		updateDataRegistro = Instant.now();
	}
	
	// metodo para enviar dados para api externa

	public void enviarParaAnalize(AnaliseFeingCliente analiseFeingCliente, Logger logger) {
		// logica aqui
		
		logger.info("Preparando proposta para envio para analise api externa...");
		
		AnaliseClienteRequest request = new AnaliseClienteRequest(id, documento, nome);
		
		// enviando para o serviço externo fazer a analize dos dados do cliente
		
		ResponseFeingCliente resultado = analiseFeingCliente.consultarAnalise(request);   // <--aguarda resposta
		
		if (resultado.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
			statusProposta = StatusPropostaClienteEnum.ELEGIVEL;
		}
		else {
			statusProposta = StatusPropostaClienteEnum.NAO_ELEGIVEL;
		}
		
		// atualizando o instante da consulta
		
		preUpdate();
		
		logger.info("Retorno da proposta concluida com sucesso! " + resultado.getResultadoSolicitacao());
	}

	
	
}
