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
                                <td>${fornecedor.id}</td>
                                <td>${fornecedor.dsFornecedor}</td>
                                <td></td>
                                <td></td>
                            </tr>`;
            $('.linhas').append(newRow);
        });
    });
}

$(document).ready(function () {
    obterFornecedores();
});

$('.obter-fornecedores').on('click', function () {
    obterFornecedores();
});
