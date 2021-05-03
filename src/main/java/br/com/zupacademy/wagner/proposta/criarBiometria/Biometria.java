package br.com.zupacademy.wagner.proposta.criarBiometria;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.wagner.proposta.associaCartaoProposta.Cartao;

@Entity
public class Biometria implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos basicos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String biometria;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataRegistro;

	// associação Cartao / uma ou mais biometria esta associada com um cartao

	@ManyToOne
	private Cartao cartao;

	// Construtor default

	@Deprecated
	public Biometria() {

	}

	// construtor com argumentos

	public Biometria(@NotBlank String biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	// getters

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}

	public Instant getDataRegistro() {
		return dataRegistro;
	}

	public Cartao getCartao() {
		return cartao;
	}

	// metodo para auxiliar para sempre que for salvar uma biometria, armazenar na
	// dataRegistro o instante atual

	@PrePersist
	public void prePersist() {
		dataRegistro = Instant.now();
	}

}
