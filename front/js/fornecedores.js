$(document).ready(function() {
    $(".iconmenu").click(function() {
        $(".paginas").toggleClass("mostrar-paginas");
    });
});

function obterFornecedores() {
    const apiUrl = 'http://localhost:8080/fornecedor';


    $('.linhas').empty();

    $.get(apiUrl, function (data) {

        data.forEach(function (fornecedor) {
            const newRow = `<tr>
                                <td></td>
                                <td>${fornecedor.idFornecedor}</td>
                                <td>${fornecedor.dsFornecedor}</td>
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
    obterFornecedores();
});

$('.obter-fornecedores').on('click', function () {
    obterFornecedores();
});
