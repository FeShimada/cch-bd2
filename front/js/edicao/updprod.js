$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        const idProduto = $("#id").val();
        const descricao = $('#descricao').val();
        const valor = parseFloat($('#valor').val());
        const quantidade = parseInt($('#qntd').val());
        const fornecedor = $('#idforn').val();

        var dados = {
            "idProduto": idProduto,
            "dsProduto": descricao,
            "nrValor": valor,
            "nrQuantidade": quantidade,
            "fornecedor": {
                "idFornecedor": fornecedor
            }
        };

        $.ajax({
            url: "http://localhost:8080/produto",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(dados),
            success: function () {
                $('#id').val('');
                $('#descricao').val('');
                $('#valor').val('');
                $('#qntd').val('');
                $('#idforn').val('');

                alert('Produto atualizado com sucesso!');
                window.history.back();
            },
            error: function () {
                alert('Erro ao atualizar o produto. Verifique a console para mais detalhes.');
            }
        });
    });
});
