var itemList = [];
$(document).ready(function () {

    $(".button").click(function () {
        var idProduto = $("#idProduto").val();
        var nrQuantidade = $("#nrQuantidade").val();

        if (idProduto && nrQuantidade) {
            var newItem = {
                produto: {
                    idProduto: idProduto
                },
                nrQuantidade: nrQuantidade
            };

            itemList.push(newItem);
            updateTable();
            clearFields();
        } else {
            alert("Preencha os campos ID do Produto e Quantidade antes de adicionar o item.");
        }
    });

    function updateTable() {
        $(".linhas").empty();
        for (var i = 0; i < itemList.length; i++) {
            var item = itemList[i];
            var newRow = $("<tr>");
            newRow.append("<td class='colmod'><button class='removeButton' data-index='" + i + "'>Remover</button></td>");
            newRow.append("<td class='colid'>" + item.produto.idProduto + "</td>");
            newRow.append("<td class='colnome'>" + item.nrQuantidade + "</td>");
            newRow.append("<td class='colmod'></td>");
            $(".linhas").append(newRow);
        }
    }

    // Adiciona um evento de clique aos botões de remoção
    $(".linhas").on("click", ".removeButton", function () {
        var index = $(this).data("index");
        itemList.splice(index, 1);
        updateTable();
    });

    function clearFields() {
        $("#idProduto").val("");
        $("#nrQuantidade").val("");
    }
});


function adicionarVenda() {
    const apiUrl = 'http://localhost:8080/venda';
    const idFuncionario = $('#idfunc').val();

    $.ajax({
        url: apiUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            funcionario: {
                idFuncionario: idFuncionario
            },
            itemList: itemList
        }),
        success: function () {
            $('#idfunc').val('');
            alert('sucesso!')
            window.history.back();
        },
        error: function (error) {
            console.error('Erro ao efetuar venda:', error);
            alert("ERRO!")
        }
    });
}

$(document).ready(function () {
    $('form').submit(function (event) {
        console.log('submiting...')
        event.preventDefault();
        adicionarVenda();
    });
});
