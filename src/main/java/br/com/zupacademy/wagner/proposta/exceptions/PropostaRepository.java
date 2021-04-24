package br.com.zupacademy.wagner.proposta.exceptions;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	// buscar proposta pelo documento
	
	Proposta findByDocumento(String documento);

}
