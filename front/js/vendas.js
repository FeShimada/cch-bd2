$(document).ready(function() {
    $(".iconmenu").click(function() {
        $(".paginas").toggleClass("mostrar-paginas");
    });
});

function obterVendas() {
    const apiUrl = 'http://localhost:8080/venda';


    $('.linhas').empty();

    $.get(apiUrl, function (data) {

        data.forEach(function (venda) {
            const newRow = `<tr>
                                <td></td>
                                <td>${venda.idVenda}</td>
                                <td>${venda.dtVenda}</td>
                                <td>${venda.nrValorTotal}</td>
                                <td>${venda.funcionario.nmFuncionario}</td>
                                <td></td>
                                <td></td>
                            </tr>`;
            $('.linhas').append(newRow);
        });
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        if (jqXHR.status === 403) {
            const errorMessage = jqXHR.responseJSON.message;
            
            alert('Erro 403: ' + errorMessage);
        } else {
            alert('Erro ao obter fornecedores. Status: ' + jqXHR.status);
        }
    });
}

$(document).ready(function () {
    obterVendas();
});

$('.obter-vendas').on('click', function () {
    obterVendas();
});


