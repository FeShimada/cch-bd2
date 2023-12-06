function adicionarFornecedor() {
    const apiUrl = 'http://localhost:8080/fornecedor';

    const id = $('#id').val();
    const descricao = $('#descricao').val();

    $.ajax({
        url: apiUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            id: id,
            dsFornecedor: descricao,
            produtoList:
        }),
        success: function () {
            
            $('#id').val('');
            $('#descricao').val('');

            window.parent.obterFornecedores();
        },
        error: function (error) {
            console.error('Erro ao adicionar fornecedor:', error);
        }
    });
}

$(document).ready(function () {
    $('form').submit(function (event) {
        event.preventDefault();
        adicionarFornecedor();
    });
});
