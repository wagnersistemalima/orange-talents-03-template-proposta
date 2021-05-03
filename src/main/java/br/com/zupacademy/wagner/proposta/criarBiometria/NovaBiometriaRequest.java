package br.com.zupacademy.wagner.proposta.criarBiometria;

import java.io.Serializable;
import java.util.Base64;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.wagner.proposta.associaCartaoProposta.Cartao;

// objeto para trafegar os dados da requisição para api

public class NovaBiometriaRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	// atributos basicos
	
	@NotBlank
	private String biometria;
	
	// construtor com argumentos
	
	@JsonCreator
	public NovaBiometriaRequest(@NotBlank String biometria) {
		
		this.biometria = biometria;
	}
	
	// getters

	public String getBiometria() {
		return biometria;
	}

	
	// metodo para converter o objeto em entidade, codificando biometria usando base64 para salvar codificada

	
	public Biometria toModel(Cartao cartao) {
		
		// logica para encodar a biometria 
		
		String biometriaCodificada = Base64.getEncoder().encodeToString(this.biometria.getBytes());
		
		return new Biometria(biometriaCodificada, cartao);
	}
	

}
