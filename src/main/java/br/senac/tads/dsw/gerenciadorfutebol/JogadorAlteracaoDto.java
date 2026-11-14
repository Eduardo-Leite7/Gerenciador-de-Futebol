package br.senac.tads.dsw.gerenciadorfutebol;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class JogadorAlteracaoDto {

	@NotBlank(message = "O nome é obrigatório.")
	@Pattern(
		regexp = ".*\\S.*\\S.*",
		message = "O nome deve ter pelo menos 2 caracteres não brancos."
	)
	private String nome;

	@NotNull(message = "O número da camisa é obrigatório.")
	@Min(value = 1, message = "O número mínimo é 1.")
	@Max(value = 99, message = "O número máximo é 99.")
	private Integer numeroCamisa;

	@NotBlank(message = "O time é obrigatório.")
	@Pattern(
		regexp = ".*\\S.*\\S.*",
		message = "O time deve ter pelo menos 2 caracteres não brancos."
	)
	private String time;

	@NotNull(message = "Informe se o jogador está lesionado.")
	private Boolean lesionado;

	private String observacoes;

	public JogadorAlteracaoDto() {
	}

	public JogadorAlteracaoDto(
		String nome,
		Integer numeroCamisa,
		String time,
		Boolean lesionado,
		String observacoes) {

		this.nome = nome;
		this.numeroCamisa = numeroCamisa;
		this.time = time;
		this.lesionado = lesionado;
		this.observacoes = observacoes;
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
