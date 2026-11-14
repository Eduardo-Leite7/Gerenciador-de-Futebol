
package br.senac.tads.dsw.gerenciadorfutebol;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

	private final JogadorService jogadorService;

	public JogadorController(JogadorService jogadorService) {

		this.jogadorService = jogadorService;

	}

	// GET - Listar todos os jogadores
	@GetMapping
	public ResponseEntity<List<JogadorDto>> obterJogadores() {

		return ResponseEntity.ok(
			jogadorService.obterJogadores()
		);

	}

	// GET - Buscar jogador pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<JogadorDto> obterJogador(
		@PathVariable Long id) {

		return jogadorService.obterJogador(id)
			.map(ResponseEntity::ok)
			.orElseGet(() ->
				ResponseEntity.notFound().build()
			);

	}

	// POST - Cadastrar jogador
	@PostMapping
	public ResponseEntity<JogadorDto> incluirJogador(
		@Valid @RequestBody JogadorDto jogador) {

		JogadorDto novoJogador =
			jogadorService.incluirNovoJogador(jogador);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(novoJogador.getId())
			.toUri();

		return ResponseEntity
			.created(location)
			.body(novoJogador);

	}

	// PUT - Alterar jogador
	@PutMapping("/{id}")
	public ResponseEntity<JogadorDto> alterarJogador(
		@PathVariable Long id,
		@Valid @RequestBody JogadorAlteracaoDto dados) {

		JogadorDto jogadorAlterado =
			jogadorService.alterarJogador(id, dados);

		return ResponseEntity.ok(jogadorAlterado);

	}

	// DELETE - Excluir jogador
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerJogador(
		@PathVariable Long id) {

		jogadorService.removerJogador(id);

		return ResponseEntity.noContent().build();

	}

}
