
package br.senac.tads.dsw.gerenciadorfutebol.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_JOGADORES")
public class JogadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Integer numeroCamisa;

	private String time;

	private Boolean lesionado;

	private String observacoes;

	// Construtor vazio
	public JogadorEntity() {
	}

	// Construtor completo
	public JogadorEntity(Long id, String nome,
						 Integer numeroCamisa, String time,
						 Boolean lesionado, String observacoes) {

		this.id = id;
		this.nome = nome;
		this.numeroCamisa = numeroCamisa;
		this.time = time;
		this.lesionado = lesionado;
		this.observacoes = observacoes;
	}

	// Getters e Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroCamisa() {
		return numeroCamisa;
	}

	public void setNumeroCamisa(Integer numeroCamisa) {
		this.numeroCamisa = numeroCamisa;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getLesionado() {
		return lesionado;
	}

	public void setLesionado(Boolean lesionado) {
		this.lesionado = lesionado;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
