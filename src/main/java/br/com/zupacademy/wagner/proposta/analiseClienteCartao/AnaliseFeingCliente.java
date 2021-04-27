package br.com.zupacademy.wagner.proposta.analiseClienteCartao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// definir o cliente web service

@Component
@FeignClient(name = "analize-cartoes", url = "${analisa.proposta.api}")
public interface AnaliseFeingCliente {
	
	// assinar a interface / envia os dados para consulta
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
	ResponseFeingCliente consultarAnalise(@NotNull @Valid AnaliseClienteRequest request);
		
	// assina a interface / recebe os dados da consulta
	
	
}
