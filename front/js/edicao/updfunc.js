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
