
const corpoTabela = document.getElementById("corpoTabela");

const mensagem = document.getElementById("mensagem");


// MOSTRAR MENSAGEM DE SUCESSO

const mensagemSalva = sessionStorage.getItem(
  "mensagemSucesso"
);

if (mensagemSalva) {

  mensagem.textContent = mensagemSalva;

  mensagem.className = "mensagem sucesso";

  sessionStorage.removeItem("mensagemSucesso");

}


// LISTAR JOGADORES

async function carregarJogadores() {

  try {

    const resposta = await fetch("/jogadores");

    if (!resposta.ok) {
      throw new Error("Erro ao buscar jogadores.");
    }

    const jogadores = await resposta.json();

    corpoTabela.innerHTML = "";

    if (jogadores.length === 0) {

      corpoTabela.innerHTML =
        '<tr><td colspan="5">Nenhum jogador cadastrado.</td></tr>';

      return;

    }

    for (const jogador of jogadores) {

      const linha = document.createElement("tr");

      const nome = document.createElement("td");
      nome.textContent = jogador.nome;

      const camisa = document.createElement("td");
      camisa.textContent = jogador.numeroCamisa;

      const time = document.createElement("td");
      time.textContent = jogador.time;

      const status = document.createElement("td");

      const etiqueta = document.createElement("span");

      etiqueta.className = jogador.lesionado
        ? "status lesionado"
        : "status normal";

      etiqueta.textContent = jogador.lesionado
        ? "LESIONADO"
        : "NORMAL";

      status.appendChild(etiqueta);

      const acoes = document.createElement("td");

      const alterar = document.createElement("a");

      alterar.className = "botao pequeno";

      alterar.textContent = "Alterar";

      alterar.href = "form.html?id=" + jogador.id;

      const excluir = document.createElement("button");

      excluir.className = "botao pequeno perigo";

      excluir.textContent = "Excluir";

      excluir.addEventListener("click", function() {

        excluirJogador(jogador.id);

      });

      acoes.append(alterar, excluir);

      linha.append(
        nome,
        camisa,
        time,
        status,
        acoes
      );

      corpoTabela.appendChild(linha);

    }

  } catch (erro) {

    mensagem.textContent = erro.message;

    mensagem.className = "mensagem erro";

  }

}


// EXCLUIR JOGADOR

async function excluirJogador(id) {

  const confirmou = window.confirm(
    "Deseja realmente excluir este jogador?"
  );

  if (!confirmou) {
    return;
  }

  try {

    const resposta = await fetch(
      "/jogadores/" + id,
      {
        method: "DELETE"
      }
    );

    if (!resposta.ok) {
      throw new Error("Erro ao excluir jogador.");
    }

    mensagem.textContent =
      "Jogador excluído com sucesso!";

    mensagem.className = "mensagem sucesso";

    await carregarJogadores();

    mensagem.textContent =
      "Jogador excluído com sucesso!";

    mensagem.className = "mensagem sucesso";

  } catch (erro) {

    mensagem.textContent = erro.message;

    mensagem.className = "mensagem erro";

  }

}


// INICIAR A LISTAGEM

carregarJogadores();
