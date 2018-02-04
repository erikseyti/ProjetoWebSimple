package br.edu.ifpr.trabalho.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private String nome;
	private String codIbge;

	public Cidade() {
		super();
	}
	
	public Cidade(String nome, String codIbge) {
		super();
		this.nome = nome;
		this.codIbge = codIbge;
	}

	public String getCodIbge() {
		return codIbge;
	}



	public void setCodIbge(String codIbge) {
		this.codIbge = codIbge;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

}
