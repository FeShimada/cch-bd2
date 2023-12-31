function adicionarFuncionario() {
    const apiUrl = 'http://localhost:8080/funcionario';
    const nome = $('#nome').val();
    const cpf = $('#cpf').val();
    const funcao = $('#funcao').val();
    const senha = $('#senha').val();

    $.ajax({
        url: apiUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nmFuncionario: nome,
            nrDocumento: cpf,
            dsSenha: senha,
            dsFuncao: funcao
        }),
        success: function () {
            $('#nome').val('');
            $('#cpf').val('');
            $('#funcao').val('');
            $('#senha').val('');
            alert('sucesso!')
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

$(document).ready(function () {
    $('form').submit(function (event) {
        console.log('submiting...')
        event.preventDefault();
        adicionarFuncionario();
    });
});
