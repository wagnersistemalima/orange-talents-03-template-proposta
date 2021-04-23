package br.com.zupacademy.wagner.proposta.novaProposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	// end point / inser / dados proposta
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> insertProposta(@Valid @RequestBody NovaPropostaRequest request) {      //1	
		
		Proposta proposta = request.toModel();                                                   //1                           
		manager.persist(proposta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()                             //1
				.path("/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
