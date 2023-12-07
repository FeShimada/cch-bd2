$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();

        var idProduto = $("#id").val();


        $.ajax({
            url: "http://localhost:8080/produto/" + idProduto,
            type: "DELETE",
            success: function () {
                $('#id').val('');

                alert('Produto deletado com sucesso!');
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
