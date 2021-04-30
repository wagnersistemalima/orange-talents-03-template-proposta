package br.com.zupacademy.wagner.proposta.novaProposta;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.StatusPropostaClienteEnum;
import br.com.zupacademy.wagner.proposta.associaCartaoProposta.Cartao;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{
	
	// buscar proposta pelo documento
	
	Proposta findByDocumento(String documento);

	void save(Cartao cartao);
	
	List<Proposta> findByStatusProposta(StatusPropostaClienteEnum status);
	
	

}
