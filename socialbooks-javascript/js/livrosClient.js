$(function () {
  let user = "murillo";
  let password = "changeme";
  let encodedStr = "Basic " + btoa(user + ":" + password);
  $(".js-load-books").on('click', function () {
    console.log(encodedStr);
    $.ajax({
      url: "http://localhost:8080/livros",
      type: "get",
      headers: {
        "Authorization": encodedStr
      },
      success: function (response) {
        desenhaTabela(response);
      }
    });
  })
});


function desenhaTabela(dados) {
  $(".js-books-table-body tr").remove();  // limpa a tabela
  for (var i = 0; i < dados.length; i++) {
    console.log(dados[i]);
    desenharLinha(dados[i]);
  }
}

function desenharLinha(linha) {
  var linhaTabela = $("<tr/>");
  $(".js.js-books-table-body").append(linhaTabela);
  linhaTabela.append("<td>" + linha.id + "</td>");
  linhaTabela.append("<td>" + linha.nome + "</td>");
  linhaTabela.append("<td>" + linha.editora + "</td>");
  linhaTabela.append("<td>" + linha.dataPublicacao + "</td>");
  linhaTabela.append("<td>" + linha.resumo + "</td>");

}

