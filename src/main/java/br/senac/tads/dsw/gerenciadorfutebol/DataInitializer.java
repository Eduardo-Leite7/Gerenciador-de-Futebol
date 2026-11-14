package br.senac.tads.dsw.gerenciadorfutebol;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!map")
public class DataInitializer implements CommandLineRunner {

	private final JogadorService jogadorService;

	public DataInitializer(JogadorService jogadorService) {

		this.jogadorService = jogadorService;

	}

	@Override
	public void run(String... args) {

		if (jogadorService.obterJogadores().isEmpty()) {

			jogadorService.incluirNovoJogador(
				new JogadorDto(
					null,
					"Vinicius Junior",
					7,
					"Real Madrid",
					false,
					"Atacante"
				)
			);

			jogadorService.incluirNovoJogador(
				new JogadorDto(
					null,
					"Neymar",
					10,
					"Santos",
					true,
					"Em recuperação"
				)
			);

		}

	}
}

