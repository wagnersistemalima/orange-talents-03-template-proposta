package br.com.zupacademy.wagner.proposta.novaProposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


// carga total = 5

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {
	
	@Autowired
	private AcaoCriacaoDaProposta acaoCriacaoDaProposta;              //1
	
	// end point / insert / dados proposta
	
	@PostMapping
	public ResponseEntity<?> insertProposta(@Valid @RequestBody NovaPropostaRequest request) {      //1	
		
		Proposta novaProposta = acaoCriacaoDaProposta.buscarUmaPossivelDocumentoNoBanco(request);  //1
		
		// validação
		
		if (novaProposta != null) {                                      //1
			
			return ResponseEntity.unprocessableEntity().build();
		}
		
		novaProposta = request.toModel();  
		
		Proposta propostaSalva = acaoCriacaoDaProposta.salvaPropostaValida(novaProposta);
		
		
		acaoCriacaoDaProposta.enviaPropostaParaAnalise(propostaSalva);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()                             //1
				.path("/{id}").buildAndExpand(propostaSalva.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	

}
