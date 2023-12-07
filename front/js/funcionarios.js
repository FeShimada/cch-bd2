$(document).ready(function() {
    $(".iconmenu").click(function() {
        $(".paginas").toggleClass("mostrar-paginas");
    });
});

function obterFuncionarios() {
    const apiUrl = 'http://localhost:8080/funcionario';


    $('.linhas').empty();

    $.get(apiUrl, function (data) {

        data.forEach(function (funcionario) {
            const newRow = `<tr>
                                <td></td>
                                <td>${funcionario.idFuncionario}</td>
                                <td>${funcionario.nmFuncionario}</td>
                                <td>${funcionario.dsFuncao}</td>
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
    obterFuncionarios();
});

$('.obter-funcionarios').on('click', function () {
    obterFuncionarios();
});

