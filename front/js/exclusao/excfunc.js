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
                error: function () {
                    alert('Erro ao deletado o funcionário. Verifique a console para mais detalhes.');
                }
            });
        }
    });
});
