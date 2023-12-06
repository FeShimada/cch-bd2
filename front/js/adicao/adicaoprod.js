function adicionarProduto() {
    const apiUrl = 'http://localhost:8080/produto';
    const descricao = $('#descricao').val();
    const valor = parseFloat($('#valor').val());
    const quantidade = parseInt($('#qntd').val());
    const fornecedor = $('#idforn').val();

    $.ajax({
        url: apiUrl,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            dsProduto: descricao,
            nrValor: valor,
            nrQuantidade: quantidade,
            fornecedor: {
                idFornecedor: fornecedor
            }
        }),
        success: function () {
            $('#descricao').val('');
            $('#valor').val('');
            $('#qntd').val('');
            $('#idforn').val('');
            alert('sucesso!')
            window.history.back();
        },
        error: function (error) {
            console.error('Erro ao adicionar produto:', error);
            alert("ERRO!")
        }
    });
}

$(document).ready(function () {
    $('form').submit(function (event) {
        console.log('submiting...')
        event.preventDefault();
        adicionarProduto();
    });
});
