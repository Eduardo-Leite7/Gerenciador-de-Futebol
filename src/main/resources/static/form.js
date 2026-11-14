
const formulario = document.getElementById("jogadorForm");

const mensagemForm = document.getElementById("mensagem");

const parametros = new URLSearchParams(window.location.search);

const id = parametros.get("id");


// CARREGAR JOGADOR PARA ALTERAÇÃO

if (id) {

  document.getElementById("tituloForm").textContent =
    "Alteração de jogador - ID " + id;

  carregarJogador(id);

}


// BUSCAR JOGADOR

async function carregarJogador(idJogador) {

  try {

    const resposta = await fetch(
      "/jogadores/" + idJogador
    );

    if (!resposta.ok) {
      throw new Error("Jogador não encontrado.");
    }

    const jogador = await resposta.json();

    formulario.nome.value = jogador.nome;

    formulario.numeroCamisa.value =
      jogador.numeroCamisa;

    formulario.time.value = jogador.time;

    formulario.observacoes.value =
      jogador.observacoes || "";

    const radio = document.querySelector(
      'input[name="lesionado"][value="' +
      jogador.lesionado + '"]'
    );

    if (radio) {
      radio.checked = true;
    }

  } catch (erro) {

    mensagemForm.textContent = erro.message;

    mensagemForm.className = "mensagem erro";

  }

}


// SALVAR JOGADOR

formulario.addEventListener("submit", async function(evento) {

  evento.preventDefault();

  const selecionado = document.querySelector(
    'input[name="lesionado"]:checked'
  );

  if (!selecionado) {

    mensagemForm.textContent =
      "Informe se o jogador está lesionado.";

    mensagemForm.className = "mensagem erro";

    return;

  }

  const dados = {

    nome: formulario.nome.value.trim(),

    numeroCamisa: Number(
      formulario.numeroCamisa.value
    ),

    time: formulario.time.value.trim(),

    lesionado: selecionado.value === "true",

    observacoes: formulario.observacoes.value

  };

  const url = id
    ? "/jogadores/" + id
    : "/jogadores";

  const metodo = id
    ? "PUT"
    : "POST";

  try {

    const resposta = await fetch(url, {

      method: metodo,

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify(dados)

    });

    if (!resposta.ok) {

      const erro = await resposta.json();

      throw new Error(
        erro.detail || erro.message ||
        "Verifique os dados informados."
      );

    }

    sessionStorage.setItem(
      "mensagemSucesso",
      id
        ? "Jogador alterado com sucesso!"
        : "Jogador cadastrado com sucesso!"
    );

    window.location.href = "index.html";

  } catch (erro) {

    mensagemForm.textContent = erro.message;

    mensagemForm.className = "mensagem erro";

  }

});
