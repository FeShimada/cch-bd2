$(document).ready(function() {
    $(".iconmenu").click(function() {
        $(".paginas").toggleClass("mostrar-paginas");
    });
});

$(document).ready(function () {
    $("button").click(function () {
        $.ajax({
            url: "http://localhost:8080/backup",
            type: "GET",
            success: function () {
                alert('Backup realizado com sucesso!');
            },
            error: function (error) {
                console.error('Erro ao realizar backup:', error);
                alert('Erro ao realizar backup. Verifique a console para mais detalhes.');
            }
        });
    });
});