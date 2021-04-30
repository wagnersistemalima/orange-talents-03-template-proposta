package br.com.zupacademy.wagner.proposta.associaCartaoProposta;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// define o cliente web service

@Component                                        // para poder ser injetado por outras classes
@FeignClient(name = "cliente-cartao", url = "${proposta.solicita.cartao}")
public interface SolicitaFeingCartao {
	
	
	// assina o metodo / envia os dados para criação do cartao
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes")
	PropostaCartaoResponse solicitaCartao(@Valid @RequestBody PropostaSolicitaCartaoRequest request);

}
