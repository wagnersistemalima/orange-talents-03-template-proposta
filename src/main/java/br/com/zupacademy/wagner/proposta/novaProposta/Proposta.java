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

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.ResponseFeingCliente;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.StatusPropostaClienteEnum;
import br.com.zupacademy.wagner.proposta.associaCartaoProposta.Cartao;

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
	
	
	@OneToOne(cascade = CascadeType.MERGE, mappedBy = "proposta")
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private StatusPropostaClienteEnum statusProposta = StatusPropostaClienteEnum.NAO_ENVIADA;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataRegistro;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updateDataRegistro;
	
	// associação com o cartao / Merge = atualiza o objeto pai para um objeto filho
	// toda vez que a proposta salvar uma atualização de cartao, a entidade filho cartao, sera atualizada
	
	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;

	
	// construtor default
	
	@Deprecated
	public Proposta() {
		
	}
	
	// construtor com argumentos
	
	public Proposta(@NotBlank String nome, @NotBlank String documento, @Email @NotBlank String email,
			@Positive @NotNull BigDecimal salario) {
		this.nome = nome;
		this.documento = documento.replaceAll("[^0-9]", "");      
		this.email = email;
		this.salario = salario;
		
	}
	
	// construtor com todos argumentos

	public Proposta(@NotBlank String nome, @NotBlank String documento, @Email @NotBlank String email,
			@Positive @NotNull BigDecimal salario, Endereco endereco, StatusPropostaClienteEnum statusProposta,
			Instant dataRegistro, Instant updateDataRegistro, Cartao cartao) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.email = email;
		this.salario = salario;
		this.endereco = endereco;
		this.statusProposta = statusProposta;
		this.dataRegistro = dataRegistro;
		this.updateDataRegistro = updateDataRegistro;
		this.cartao = cartao;
	}

	// getters

	public String getDocumento() {
		return documento;
	}
	
	

	public Cartao getCartao() {
		return cartao;
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
	

	public Instant getDataRegistro() {
		return dataRegistro;
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
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
	
	// metodo para atualizar status da proposta
	
	public void atualizaStatusDaproposta(ResponseFeingCliente resultado) {
		
		if (resultado.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
			statusProposta = StatusPropostaClienteEnum.ELEGIVEL;
		}
		else {
			statusProposta = StatusPropostaClienteEnum.NAO_ELEGIVEL;
		}
		
	}
	
	// metodo adiciona cartao a proposta
	
	public void adicionaCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	// metodo para adicionar endereço

	public void adicionaEndereco(@Valid NovaPropostaRequest request) {
		this.endereco = new Endereco(request.getLogradouro(), request.getBairro(), request.getComplemento(), request.getUf(), this);
		
	}
	
}
