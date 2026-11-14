package br.senac.tads.dsw.gerenciadorfutebol;

import java.util.List;
import java.util.Optional;

public interface JogadorService {

	// Listar todos os jogadores
	List<JogadorDto> obterJogadores();

	// Buscar jogador pelo ID
	Optional<JogadorDto> obterJogador(Long id);

	// Cadastrar jogador
	JogadorDto incluirNovoJogador(JogadorDto jogador);

	// Alterar jogador
	JogadorDto alterarJogador(
		Long id,
		JogadorAlteracaoDto dados
	);

	// Excluir jogador
	void removerJogador(Long id);

}
