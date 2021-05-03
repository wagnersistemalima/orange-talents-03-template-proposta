package br.com.zupacademy.wagner.proposta.criarBiometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.wagner.proposta.associaCartaoProposta.Cartao;

// carga = 6

@RestController
@RequestMapping(value = "/biometrias")
public class NovaBiometriaController {
	
	// 1
	private final Logger logger = LoggerFactory.getLogger(NovaBiometriaController.class);
	
	@PersistenceContext
	private EntityManager manager;
	
	// end point / inserir biometria / resposta 201 created
	
	@Transactional
	@PostMapping(value = "/{id}")                                                                       //1
	public ResponseEntity<?> insertBiometria(@PathVariable("id") String id, @Valid @RequestBody NovaBiometriaRequest request) {
		
		logger.info("Iniciando cadastro biometria....");
		
		Cartao cartao = manager.find(Cartao.class, id);                        //1
		
		if (cartao == null) {                               //1
			logger.warn("O cartão não foi encontrado!");
			return ResponseEntity.notFound().build();
		}
		
		
		logger.info("cadastrando biometria........");
		
		// logica aqui
		
		Biometria biometria = request.toModel(cartao);                    //1
		
		manager.persist(biometria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()                       //1
				.path("/{id}").buildAndExpand(request.getBiometria())
				.toUri();
		
		logger.info("Biometria cadastrada com sucesso!");
		
		return ResponseEntity.created(uri).build();
		
		
	}

}
