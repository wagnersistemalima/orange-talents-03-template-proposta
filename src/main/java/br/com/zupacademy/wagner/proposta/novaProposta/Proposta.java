package br.com.zupacademy.wagner.proposta.novaProposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// atributos basico
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String documento;
	
	@Email
	@NotBlank
	private String email;
	
	@Positive
	@NotNull
	private BigDecimal salario;
	
	// associação com endereco
	
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private Endereco endereco;
	
	// construtor default
	
	@Deprecated
	public Proposta() {
		
	}
	
	// construtor com argumentos
	
	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @Positive @NotNull BigDecimal salario,
			@NotNull @Valid Endereco endereco) {
		this.documento = documento;
		this.email = email;
		this.salario = salario;
		this.endereco = endereco;
	}



	// getters

	public String getDocumento() {
		return documento;
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
	
	

}
