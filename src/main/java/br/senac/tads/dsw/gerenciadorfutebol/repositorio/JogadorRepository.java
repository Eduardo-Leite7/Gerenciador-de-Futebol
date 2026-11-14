package br.senac.tads.dsw.gerenciadorfutebol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.gerenciadorfutebol.entidade.JogadorEntity;

public interface JogadorRepository
	extends JpaRepository<JogadorEntity, Long> {

}
