package br.com.zupacademy.wagner.proposta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.wagner.proposta.analiseClienteCartao.AnaliseClienteRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@AutoConfigureDataJpa
public class AnaliseClienteControllerTeste {

	// atributos basicos

	private Long idPropostaExistente;
	private Long idPropostaNaoExistente;

	private String documentoExistente;
	private String documentoNaoExistenteNoBanco;

	private String nomeValidoDonoDocumento;
	private String nomeNaoValidoDonoDocumento;

	// desserializar

	@Autowired
	private ObjectMapper mapper;

	// permite levantar requisição sem levantar o servidor

	@Autowired
	private MockMvc mockMvc;
	
	// acessar o banco para fazer as pesquisas
	
	@PersistenceContext
	private EntityManager manager;


	// setup do contexto / recarrega antes de cada teste

	@BeforeEach
	public void setup() {
		idPropostaExistente = 1L;
		idPropostaNaoExistente = 5000L;

		documentoExistente = "46580194006";
		documentoNaoExistenteNoBanco = "80705577023";

		nomeValidoDonoDocumento = "cliente1 da Silva";
		nomeNaoValidoDonoDocumento = "nome desconhecido";
	}

	// 1 cenario de teste / deve retornar 200 ok quando o cliente fizer a consulta com os dados validos,
	// e que estejam cadastrados no banco
	
	@Test
	public void deveRetornar200QuandoAPropostaExiste() throws JsonProcessingException, Exception {
		AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaExistente, documentoExistente,
				nomeValidoDonoDocumento);

		mockMvc.perform(get("/propostas/*") // uri da requisição

				.contentType("application/json") // cabeçario json

				.content(mapper.writeValueAsString(request))) // conteudo

				.andExpect(status().isOk()); // resultado esperado 200
	}
	
	// 2º cenario de testes / deve retorna 404 not found / caso o id da proposta não exista, nome não
	// exista, documento naõ exista
	
	@Test
	public void deveRetornar404QuandoIdPropostaNaoExiste() throws JsonProcessingException, Exception {
		AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaNaoExistente, documentoNaoExistenteNoBanco,
				nomeNaoValidoDonoDocumento);

		mockMvc.perform(get("/propostas/*") // uri da requisição

				.contentType("application/json") // cabeçario json

				.content(mapper.writeValueAsString(request))) // conteudo

				.andExpect(status().isNotFound()); // resultado esperado 404
	}
	
	// 3º cenario / deve retornar 404 / caso o id da proposta exista, mas o documento não existe, e o
	// nome também não exista
	
	@Test
	public void deveRetornar404QuandoNomeInvalidoEDocumentoInvalido() throws JsonProcessingException, Exception {
		AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaExistente, documentoNaoExistenteNoBanco,
				nomeNaoValidoDonoDocumento);

		mockMvc.perform(get("/propostas/*") // uri da requisição

				.contentType("application/json") // cabeçario json

				.content(mapper.writeValueAsString(request))) // conteudo

				.andExpect(status().isNotFound()); // resultado esperado 404
	}
	
	// 4º cenario / deve retornar 404 / caso o id da proposta exista, o documento existe,
	// mas o nome não exista
	
	@Test
	public void deveRetornar404QuandoNomeInvalidoEDocumentoValido() throws JsonProcessingException, Exception {
		AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaExistente, documentoExistente,
				nomeNaoValidoDonoDocumento);

		mockMvc.perform(get("/propostas/*") // uri da requisição

				.contentType("application/json") // cabeçario json

				.content(mapper.writeValueAsString(request))) // conteudo

				.andExpect(status().isNotFound()); // resultado esperado 404
	}
	
	// 5º cenario de teste / deve retornar 404 / caso o id da proposta exista, o documento não exista, mas 
	// o nome exista
	
	@Test
	public void deveRetornar404QuandoNomeValidoEDocumentoNaoValido() throws JsonProcessingException, Exception {
		AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaExistente, documentoNaoExistenteNoBanco,
				nomeValidoDonoDocumento);

		mockMvc.perform(get("/propostas/*") // uri da requisição

				.contentType("application/json") // cabeçario json

				.content(mapper.writeValueAsString(request))) // conteudo

				.andExpect(status().isNotFound()); // resultado esperado 404
	}
	
	// 6º cenario de teste / deve retornar 404 / caso o id da proposta nao exista, o documento existe, e o
		// o nome existe
		
		@Test
		public void deveRetornar404QuandoNomeValidoEDocumentoValidoIdNaoValido() throws JsonProcessingException, Exception {
			AnaliseClienteRequest request = new AnaliseClienteRequest(idPropostaNaoExistente, documentoExistente,
					nomeValidoDonoDocumento);

			mockMvc.perform(get("/propostas") // uri da requisição

					.contentType("application/json") // cabeçario json

					.content(mapper.writeValueAsString(request))) // conteudo

					.andExpect(status().isNotFound()); // resultado esperado 404
		}

}
