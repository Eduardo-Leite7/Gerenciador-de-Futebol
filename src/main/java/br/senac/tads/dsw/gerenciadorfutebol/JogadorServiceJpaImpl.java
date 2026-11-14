package br.senac.tads.dsw.gerenciadorfutebol;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senac.tads.dsw.gerenciadorfutebol.entidade.JogadorEntity;
import br.senac.tads.dsw.gerenciadorfutebol.repositorio.JogadorRepository;

@Service
@Primary
@Profile("!map")
@Transactional
public class JogadorServiceJpaImpl implements JogadorService {

	private final JogadorRepository jogadorRepository;

	public JogadorServiceJpaImpl(
		JogadorRepository jogadorRepository) {

		this.jogadorRepository = jogadorRepository;
	}

	// Listar jogadores
	@Override
	@Transactional(readOnly = true)
	public List<JogadorDto> obterJogadores() {

		return jogadorRepository.findAll()
			.stream()
			.map(this::converterParaDto)
			.toList();
	}

	// Buscar jogador pelo ID
	@Override
	@Transactional(readOnly = true)
	public Optional<JogadorDto> obterJogador(Long id) {

		return jogadorRepository.findById(id)
			.map(this::converterParaDto);
	}

	// Cadastrar jogador
	@Override
	public JogadorDto incluirNovoJogador(JogadorDto jogador) {

		JogadorEntity entidade = new JogadorEntity();

		entidade.setNome(jogador.getNome());
		entidade.setNumeroCamisa(jogador.getNumeroCamisa());
		entidade.setTime(jogador.getTime());
		entidade.setLesionado(jogador.getLesionado());
		entidade.setObservacoes(jogador.getObservacoes());

		JogadorEntity salvo = jogadorRepository.save(entidade);

		return converterParaDto(salvo);
	}

	// Alterar jogador
	@Override
	public JogadorDto alterarJogador(
		Long id,
		JogadorAlteracaoDto dados) {

		JogadorEntity entidade = jogadorRepository.findById(id)
			.orElseThrow(() ->
				new NaoEncontradoException(
					"Jogador não encontrado: " + id
				)
			);

		entidade.setNome(dados.getNome());
		entidade.setNumeroCamisa(dados.getNumeroCamisa());
		entidade.setTime(dados.getTime());
		entidade.setLesionado(dados.getLesionado());
		entidade.setObservacoes(dados.getObservacoes());

		JogadorEntity salvo = jogadorRepository.save(entidade);

		return converterParaDto(salvo);
	}

	// Excluir jogador
	@Override
	public void removerJogador(Long id) {

		JogadorEntity entidade = jogadorRepository.findById(id)
			.orElseThrow(() ->
				new NaoEncontradoException(
					"Jogador não encontrado: " + id
				)
			);

		jogadorRepository.delete(entidade);
	}

	// Converter Entity para DTO
	private JogadorDto converterParaDto(JogadorEntity entidade) {

		return new JogadorDto(
			entidade.getId(),
			entidade.getNome(),
			entidade.getNumeroCamisa(),
			entidade.getTime(),
			entidade.getLesionado(),
			entidade.getObservacoes()
		);
	}
}
