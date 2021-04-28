package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

@RestController
@RequestMapping(value = "/propostas")
public class AnaliseClienteController {
	
	// carga = 5
	
	
	private final Logger logger = LoggerFactory.getLogger(AnaliseClienteController.class);     //1
	
	
	@PersistenceContext
	private EntityManager manager;
	
	// end point / analizar o andamento da proposta do cliente / get
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<?> findById(@Valid @RequestBody AnaliseClienteRequest request) {     //1
		
		logger.info("Inicio da pesquisa da proposta");
		
		Proposta proposta = request.toModel(manager);               //1
		
		// validação
		
		if (proposta == null || !proposta.getDocumento().equals(request.getDocumento()) || 
				!proposta.getNome().equals(request.getNome())) { //1
			
			logger.warn("A pesquisa da proposta teve algum dado informado divergente");
			
			return ResponseEntity.notFound().build();
		}
		
		
			logger.info("Pesquisa realizada com sucesso! " + proposta.getId());
		
		//consulta dados da proposta
		
		return ResponseEntity.ok().body(new AnaliseClienteResponse(proposta));                            //1
		
	}
	
}
