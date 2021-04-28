package br.com.zupacademy.wagner.proposta.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zupacademy.wagner.proposta.novaProposta.NovaPropostaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class NovaPropostaControllerTeste {
	
	@Autowired
	private MockMvc mockMvc;                         // permite levantar requisição sem levantar o servidor

	@Autowired
	private ObjectMapper mapper;                      // desserializar
	
	private String nome;
	private String documentoValido;
	private String documentoInvalido;
	private String documentoJaCadastrado;
	private String email;
	
	private String logradouro;
	private String bairro;
	private String complemento;
	private String uf;
	
	private BigDecimal salario;
	private BigDecimal salarioNegativo;
	private BigDecimal salarioZerado;
	
	// setup do contexto / recarrega antes de cada teste 
	
	@BeforeEach
	public void setup() {
		nome = "cliente1 da Silva";
		documentoValido = "22492552020";
		documentoInvalido = "465801940";
		documentoJaCadastrado = "465.801.940-06";
		email = "cliente1@gmail.com";
		
		logradouro = "Rua das meninas, 15";
		bairro = "Jose pinheiro";
		complemento = "Proximo a loteria";
		uf = "PB";
		
		salario = new BigDecimal(2000);
		salarioNegativo = new BigDecimal(-1);
		salarioZerado = new BigDecimal(0);
		
	}
	
	
	// 1 cenario de teste / retorna status 201 guando a proposta é criada com sucesso
	
	
	@Test
	public void deveCriarPropostaComSucessoRetornando201Created() throws JsonProcessingException, Exception {
		
		NovaPropostaRequest request = new NovaPropostaRequest(nome, documentoValido, email, logradouro, bairro, complemento, uf, salario);
		
		
		
		mockMvc.perform(post("/propostas")                      //uri da requisição
				
				.contentType("application/json")                 // cabeçario json    
				
				.content(mapper.writeValueAsString(request)))      // conteudo
		
				.andExpect(status().isCreated());                  // resultado esperado 201
		
	}
	
	// 2º cenario de teste / retorna 400 / guando o documento Invalido
	
	@Test
	public void deveRetornar400QuandoDocumentoNaoValidado() throws JsonProcessingException, Exception {
		
		NovaPropostaRequest request = new NovaPropostaRequest(nome, documentoInvalido, email, logradouro, bairro, complemento, uf, salario);
		
		
		
		mockMvc.perform(post("/propostas")                      //uri da requisição
				
				.contentType("application/json")                 // cabeçario json    
				
				.content(mapper.writeValueAsString(request)))      // conteudo
		
				.andExpect(status().isBadRequest());                  // resultado esperado 400
		
	}
	
	// 3º cenario de teste / retorna 422 / guando o documento já estiver cadastrado
	
	@Test
	public void deveRetornar422QuandoDocumentoJaEstiverCadastrado() throws JsonProcessingException, Exception {
		
		NovaPropostaRequest request = new NovaPropostaRequest(nome, documentoJaCadastrado, email, logradouro, bairro, complemento, uf, salario);
		
		
		
		mockMvc.perform(post("/propostas")                      //uri da requisição
				
				.contentType("application/json")                 // cabeçario json    
				
				.content(mapper.writeValueAsString(request)))      // conteudo
		
				.andExpect(status().isUnprocessableEntity());                  // resultado esperado 422
		
	}
	
	// 4º cenario de teste / retorna 400 / quando o salario for negativo
	
	@Test
	public void deveRetornar400QuandoSalarioNegativo() throws JsonProcessingException, Exception {
		
		NovaPropostaRequest request = new NovaPropostaRequest(nome, documentoValido, email, logradouro, bairro, complemento, uf, salarioNegativo);
		
		
		
		mockMvc.perform(post("/propostas")                      //uri da requisição
				
				.contentType("application/json")                 // cabeçario json    
				
				.content(mapper.writeValueAsString(request)))      // conteudo
		
				.andExpect(status().isBadRequest());                  // resultado esperado 400
		
	}
	
	// 5º cenario de teste / retorna 400 / quando o salario for zero
	
		@Test
		public void deveRetornar400QuandoSalarioZero() throws JsonProcessingException, Exception {
			
			NovaPropostaRequest request = new NovaPropostaRequest(nome, documentoValido, email, logradouro, bairro, complemento, uf, salarioZerado);
			
			
			
			mockMvc.perform(post("/propostas")                      //uri da requisição
					
					.contentType("application/json")                 // cabeçario json    
					
					.content(mapper.writeValueAsString(request)))      // conteudo
			
					.andExpect(status().isBadRequest());                  // resultado esperado 400
			
		}

}
