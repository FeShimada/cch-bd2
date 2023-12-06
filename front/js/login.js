document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();

        var nome = document.querySelector("[name=nome]").value;
        var senha = document.querySelector("[name=senha]").value;

        var dados = {
            usuario: nome,
            senha: senha
        };

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/database-user/login", true);
        xhr.setRequestHeader("Content-Type", "application/json");

        var dadosJSON = JSON.stringify(dados);
        xhr.send(dadosJSON);

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log("Login bem-sucedido!");
                alert('sucesso!')
                window.location.href = "paginainicial.html";
            } else {
                console.error("Erro durante o login. Código de status: " + xhr.status);
                alert("Erro durante o login.")
            }
        };
    });
});
