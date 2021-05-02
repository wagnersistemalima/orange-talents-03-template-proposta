package br.com.zupacademy.wagner.proposta.associaCartaoProposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.zupacademy.wagner.proposta.novaProposta.Proposta;

// entidade

@Entity
public class Cartao implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos basicos

	@Id
	private String id;

	private String emitidoEm;

	private String titular;

	private BigDecimal limite;
	
	// associação com a proposta / merge = quando atualizar o objeto pai = proposta, tambem será atualizada
	// no banco as informaçoes do objeto filho= cartao
	
	
	
	
	// construtor default
	
	@Deprecated
	public Cartao() {
		
	}
	
	// construtor com argumentos
	
	
	public Cartao(String id, String emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		
	}

	// getters

	public String getId() {
		return id;
	}

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}
	
	
	
	// HashCode $ equals comparando pelo id

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
