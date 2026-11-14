
package br.senac.tads.dsw.gerenciadorfutebol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class JogadorServiceMapImpl implements JogadorService {

	private final Map<Long, JogadorDto> jogadores =
		new HashMap<>();

	private Long proximoId = 1L;

	public JogadorServiceMapImpl() {

		incluirNovoJogador(new JogadorDto(
			null,
			"Vinicius Junior",
			7,
			"Real Madrid",
			false,
			"Atacante"
		));

		incluirNovoJogador(new JogadorDto(
			null,
			"Neymar",
			10,
			"Santos",
			true,
			"Em recuperação"
		));
	}

	@Override
	public List<JogadorDto> obterJogadores() {

		return new ArrayList<>(jogadores.values());
	}

	@Override
	public Optional<JogadorDto> obterJogador(Long id) {

		return Optional.ofNullable(jogadores.get(id));
	}

	@Override
	public JogadorDto incluirNovoJogador(JogadorDto jogador) {

		jogador.setId(proximoId);

		jogadores.put(proximoId, jogador);

		proximoId++;

		return jogador;
	}

	@Override
	public JogadorDto alterarJogador(
		Long id,
		JogadorAlteracaoDto dados) {

		JogadorDto jogador = jogadores.get(id);

		if (jogador == null) {
			throw new NaoEncontradoException(
				"Jogador não encontrado: " + id
			);
		}

		jogador.setNome(dados.getNome());
		jogador.setNumeroCamisa(dados.getNumeroCamisa());
		jogador.setTime(dados.getTime());
		jogador.setLesionado(dados.getLesionado());
		jogador.setObservacoes(dados.getObservacoes());

		return jogador;
	}

	@Override
	public void removerJogador(Long id) {

		if (jogadores.remove(id) == null) {

			throw new NaoEncontradoException(
				"Jogador não encontrado: " + id
			);

		}
	}
}
