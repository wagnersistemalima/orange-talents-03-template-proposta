package br.com.zupacademy.wagner.proposta.novaProposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	// buscar proposta pelo documento
	
	Proposta findByDocumento(String documento);

}
