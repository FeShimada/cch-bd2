$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        var confirmacao = confirm("Atenção! Caso você exclua esse fornecedor, estará excluindo todos os seus produtos. Tem certeza?");
        
        if (confirmacao) {
            var idFornecedor = $("#id").val();

            $.ajax({
                url: "http://localhost:8080/fornecedor/" + idFornecedor,
                type: "DELETE",
                success: function () {
                    $('#id').val('');
                    alert('Fornecedor deletado com sucesso!');
                    window.history.back();
                },
                error: function () {
                    alert('Erro ao deletar o fornecedor. Verifique a console para mais detalhes.');
                }
            });
        } else {
            alert('Exclusão cancelada.');
        }
    });
});
