$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        const idFuncionario = $("#id").val();
        const nome = $('#nome').val();
        const cpf = $('#cpf').val();
        const funcao = $('#funcao').val();
        const senha = $('#senha').val();

        var dados = {
            "idFuncionario": idFuncionario,
            "nmFuncionario": nome,
            "nrDocumento": cpf,
            "dsSenha": senha,
            "dsFuncao": funcao
        };

        $.ajax({
            url: "http://localhost:8080/funcionario",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(dados),
            success: function () {
                $('#id').val('');
                $('#nome').val('');
                $('#cpf').val('');
                $('#funcao').val('');
                $('#senha').val('');

                alert('Funcionário atualizado com sucesso!');
                window.history.back();
            },
            error: function () {
                alert('Erro ao atualizar o funcionario. Verifique a console para mais detalhes.');
            }
        });
    });
});
