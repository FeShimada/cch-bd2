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
            error: function () {
                alert('Erro ao deletado o produto. Verifique a console para mais detalhes.');
            }
        });

    });
});
