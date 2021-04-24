package br.com.zupacademy.wagner.proposta.novaProposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.wagner.proposta.exceptions.PropostaRepository;

// carga total = 5

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {
	
	@Autowired
	private PropostaRepository repository;                                    //1
	
	
	@PersistenceContext
	private EntityManager manager;
	
	// end point / inser / dados proposta
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> insertProposta(@Valid @RequestBody NovaPropostaRequest request) {      //1	
		
		Proposta novaProposta = repository.findByDocumento(request.getDocumento());             //1
		
		if (novaProposta != null) {                                      //1
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Proposta proposta = request.toModel();                                                                              
		manager.persist(proposta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()                             //1
				.path("/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
