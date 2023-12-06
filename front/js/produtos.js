$(document).ready(function() {
    $(".iconmenu").click(function() {
        $(".paginas").toggleClass("mostrar-paginas");
    });
});

function obterProdutos() {
    const apiUrl = 'http://localhost:8080/produto';


    $('.linhas').empty();

    $.get(apiUrl, function (data) {

        data.forEach(function (produto) {
            const newRow = `<tr>
                                <td></td>
                                <td>${produto.idProduto}</td>
                                <td>${produto.dsProduto}</td>
                                <td>${produto.nrValor}</td>
                                <td>${produto.nrQuantidade}</td>
                                <td>${produto.fornecedor.dsFornecedor}</td>
                                <td></td>
                                <td></td>
                            </tr>`;
            $('.linhas').append(newRow);
        });
    });
}

$(document).ready(function () {
    obterProdutos();
});

$('.obter-produtos').on('click', function () {
    obterProdutos();
});

