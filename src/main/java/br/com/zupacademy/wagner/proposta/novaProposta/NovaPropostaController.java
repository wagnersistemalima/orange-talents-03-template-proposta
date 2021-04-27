package br.com.zupacademy.wagner.proposta.novaProposta;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseFeingCliente;


// carga total = 7

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {
	
	private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);   //1
	
	@Autowired
	private AnaliseFeingCliente analiseFeingCliente;                                //1
	
	@Autowired
	private PropostaRepository repository;                                    //1
	
	// end point / inser / dados proposta
	
	@Transactional
	@PostMapping
	public ResponseEntity<?> insertProposta(@Valid @RequestBody NovaPropostaRequest request) {      //1	
		
		logger.info("Inicio da criação da proposta....");
		
		Proposta novaProposta = repository.findByDocumento(request.getDocumento());             //1
		
		// validação
		
		if (novaProposta != null) {                                      //1
			
			logger.warn("Proposta não foi criada, já existe proposta para este documento....");
			
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Proposta proposta = request.toModel();                                                                              
		repository.save(proposta);
		
		logger.info("Proposta criada com sucesso " + proposta.getId());
		
		// metodo enviar a proposta para analize Feing Cliente / Api externa /
		
		proposta.enviarParaAnalize(analiseFeingCliente, logger);
		
		repository.save(proposta);
		
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()                             //1
				.path("/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
