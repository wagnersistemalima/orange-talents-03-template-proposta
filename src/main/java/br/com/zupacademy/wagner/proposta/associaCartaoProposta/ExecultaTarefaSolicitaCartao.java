package br.com.zupacademy.wagner.proposta.associaCartaoProposta;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.StatusPropostaClienteEnum;
import br.com.zupacademy.wagner.proposta.novaProposta.NovaPropostaController;
import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;
import br.com.zupacademy.wagner.proposta.novaProposta.PropostaRepository;

// classe contendo a logica de enviar as propostas validas para gerar cartao na api externa
// sistema de cartao                  carga = 9

@Component
public class ExecultaTarefaSolicitaCartao {

	// 1
	private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);

	// 1 dependencia para api externa de cartoes

	@Autowired
	private SolicitaFeingCartao solicitaFeingCartao;

	// 1 dependencia para repository;

	@Autowired
	private PropostaRepository repository;
	
	
	// 1 metodo que verifica tempos em tempos as propostas com status Elegivel, e envia para api externa
	// para associar a um cartao

	@Scheduled(fixedDelay = 20000) // tempo de espera em milissegundos
	public void execultaSolicitacaoCartao() {

		logger.info("Execultando tarefa agendada solicitação cartao!");
		
		//1
		List<Proposta> listaPropostaSemCartao = repository.findByStatusProposta(StatusPropostaClienteEnum.ELEGIVEL);

		logger.info("Preparação da proposta para envio ão sistema de geração de cartao");
		

		for (Proposta proposta : listaPropostaSemCartao) {
			
			if (proposta.getCartao() != null) {  //1
				logger.info("Esta proposta já esta associada com cartão, idProposta = " + proposta.getId());
			}
			else {  //1
				String idProposta = String.valueOf(proposta.getId());
				
				//1
				PropostaSolicitaCartaoRequest request = new PropostaSolicitaCartaoRequest(proposta.getDocumento(),
						proposta.getNome(), idProposta);
				
				//1
				PropostaCartaoResponse resultado = solicitaFeingCartao.solicitaCartao(request);
				
				logger.info("Resultado da solicitação chegando!");
				
				//1
				Cartao cartao = resultado.toModel(proposta);
					
				logger.info("Criando cartão....");
				
				proposta.adicionaCartao(cartao);

				repository.save(proposta);

				logger.info("Salvando cartao no banco...." + cartao.getId());
			}
			
							
		}

	}
	
	

}
