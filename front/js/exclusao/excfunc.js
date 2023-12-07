$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        var idFuncionario = $("#id").val();

        var confirmacao = confirm("Atenção! Caso você exclua esse funcionário, estará excluindo todas as suas vendas. Tem certeza?");

        if (confirmacao) {
            $.ajax({
                url: "http://localhost:8080/funcionario/" + idFuncionario,
                type: "DELETE",
                success: function () {
                    $('#id').val('');
    
                    alert('Funcionário deletado com sucesso!');
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
        }
    });
});
