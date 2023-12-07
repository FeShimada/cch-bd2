$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        var idFornecedor = $("#id").val();
        var dsFornecedor = $("#descricao").val();

        var dados = {
            "idFornecedor": idFornecedor,
            "dsFornecedor": dsFornecedor
        };

        $.ajax({
            url: "http://localhost:8080/fornecedor",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(dados),
            success: function () {
                $('#id').val('');
                $('#descricao').val('');

                alert('Fornecedor atualizado com sucesso!');
                window.history.back();
            },
            error: function (error) {
                if (error.responseJSON && error.responseJSON.message) {
                    alert('Erro: ' + error.responseJSON.message);
                } else {
                    alert('Erro desconhecido.');
                }
            }
        });
    });
});
