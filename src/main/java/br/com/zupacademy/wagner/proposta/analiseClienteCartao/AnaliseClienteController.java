package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;
import br.com.zupacademy.wagner.proposta.novaProposta.PropostaResponse;

@RestController
@RequestMapping(value = "/propostas")
public class AnaliseClienteController {
	
	// carga = 5
	
	
	private final Logger logger = LoggerFactory.getLogger(AnaliseClienteController.class);     //1
	
	
	@PersistenceContext
	private EntityManager manager;
	
	// end point / analizar o andamento da proposta do cliente / get
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@Valid  @PathVariable("id") @NotNull Long id) {     //1
		
		logger.info("Inicio da pesquisa da proposta");
		
		Proposta proposta = manager.find(Proposta.class, id);               //1
		
		// validação
		
		if (proposta == null) { //1
			
			logger.warn("A proposta não foi achada no banco");
			
			return ResponseEntity.notFound().build();
		}
		
		PropostaResponse response = new PropostaResponse(proposta);
			
		logger.info("Pesquisa realizada com sucesso! " + proposta.getId());
		
		//consulta dados da proposta
		
		return ResponseEntity.ok().body(response);                            //1
		
	}
	
}
