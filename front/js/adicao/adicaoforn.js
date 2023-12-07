function adicionarFornecedor() {
    const apiUrl = 'http://localhost:8080/fornecedor';
    const descricao = $('#descricao').val();

    $.ajax({
        url: apiUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            dsFornecedor: descricao
        }),
        success: function () {
            $('#descricao').val('');
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
        event.preventDefault();
        adicionarFornecedor();
    });
});
