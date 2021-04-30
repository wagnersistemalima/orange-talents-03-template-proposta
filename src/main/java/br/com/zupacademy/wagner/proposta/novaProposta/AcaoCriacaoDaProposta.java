package br.com.zupacademy.wagner.proposta.novaProposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseClienteRequest;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseFeingCliente;
import br.com.zupacademy.wagner.proposta.analiseClienteCartao.ResponseFeingCliente;
import feign.FeignException;

// Classe contendo a logica de execultar açoes relacionadas ao envio das propostas.

@Component // para poder ser injetada por outras classes
public class AcaoCriacaoDaProposta {

	// 1
	private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);

	// 1 dependencia para o repository

	@Autowired
	private PropostaRepository repository;

	// 1 dependencia para api externa de analise

	@Autowired
	private AnaliseFeingCliente analisaFeingCliente;

	
	// 1º metodo buscar uma possivel proposta no banco

	public Proposta buscarUmaPossivelDocumentoNoBanco(NovaPropostaRequest request) {

		logger.info("Inicio da criação da proposta....");

		Proposta novaProposta = repository.findByDocumento(request.getDocumento());

		if (novaProposta != null) {
			logger.info("Proposta não foi criada, já existe proposta para este documento....");
		}
		return novaProposta;
	}

	// 2º metodo para salvar uma proposta valida

	public Proposta salvaPropostaValida(Proposta proposta) {
		repository.save(proposta);

		logger.info("Proposta salva no banco com sucesso! id = " + proposta.getId());
		return proposta;
	}

	// 3º metodo que envia a proposta para analize

	public void enviaPropostaParaAnalise(Proposta proposta) {

		logger.info("Preparando proposta para envio para analise api externa...");

		AnaliseClienteRequest request = new AnaliseClienteRequest(proposta.getId(), proposta.getDocumento(),
				proposta.getNome());

		ResponseFeingCliente resultado;
		
		try {
			resultado = analisaFeingCliente.consultarAnalise(request);
			
			logger.info("Retorno da proposta analizada concluida com sucesso! " + resultado.getResultadoSolicitacao());

			proposta.atualizaStatusDaproposta(resultado);


		} catch (FeignException e) {	// servidor devolvendo status não elegivel estora 500 exception
			
			resultado = new ResponseFeingCliente("NAO_ELEGIVEL");
			proposta.atualizaStatusDaproposta(resultado);
		}

		
		repository.save(proposta);

		logger.info("status da proposta salva no banco com sucesso! status = " + proposta.getStatusProposta());

	}

	

}
