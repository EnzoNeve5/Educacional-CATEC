<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
    <link rel="stylesheet" href="catec.css">
    <title>Página Principal</title>
    <style>
        body {
            background-color: white;
        }
        input[type="search"] {
            background-color: rgb(220, 220, 220);
            color: blue;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            padding: 8px;
            border: none;
            font-size: 16px;
            width: 320px;
        }
        button {
            background-color: lightblue;
            color: blue;
            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
            padding: 8px;
            border: 0px solid transparent;
            font-size: 16px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background-color: #f0f0f0;
            padding: 15px;
            margin: 10px 0;
            border-radius: 15px;
            box-shadow: 0px 2px 5px rgba(0,0,0,0.1);
            font-family: Arial, sans-serif;
        }
        .caixa-texto {
            background: #ccc;
            border-radius: 20px;
            padding: 15px;
            margin: 15px auto;
            width: 60%;
            box-shadow: 0 4px 10px rgba(0,0,0,0.08);
        }
        .popup, .secondpopup {
            display: none;
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background: #ccc;
            border: 1px solid #ccc;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            z-index: 1000;
        }
        .popup .fechar, .secondpopup .fechardois {
            cursor: pointer;
            float: right;
            font-size: 20px;
            font-weight: bold;
        }
        .popup .fechar:hover, .secondpopup .fechardois:hover {
            color: red;
        }
    </style>
    <script>
        function mostrarPopup() {
            document.getElementById('meuPopUp').style.display = 'block';
        }

        function fecharPopup() {
            document.getElementById('meuPopUp').style.display = 'none';
        }

        function aparecerPopup() {
            document.getElementById('meusegundoPopUp').style.display = 'block';
        }

        function desabrirPopup() {
            document.getElementById('meusegundoPopUp').style.display = 'none';
        }
    </script>
</head>
<body>
    <div class="topnav">
        <img src="catec-pequeno.png" alt="CATEC">
        <form action="" method="POST">
            <input type="search" id="search" name="search" placeholder="Pesquisar...">
            <input type="submit" value="Pesquisar">
        </form>
        <button onclick="mostrarPopup()">Upload Ví­deo</button>
        <div id="meuPopUp" class="popup">
            <div class="conteudo-popup">
                <span class="fechar" onclick="fecharPopup()">&times;</span>
                <p>Qual ví­deo você deseja postar?</p>
            </div>
        </div>
        <button onclick="aparecerPopup()">Escrever Texto</button>
        <div id="meusegundoPopUp" class="secondpopup">
            <div class="conteudo-segundopopup">
                <span class="fechardois" onclick="desabrirPopup()">&times;</span>
                <p>O que você deseja escrever?</p>
                <form action="EnviarTextoServlet" method="POST">
                    <textarea id="texto" name="texto" rows="4" cols="50"></textarea><br>
                    <input type="submit" value="Enviar Texto">
                </form>
            </div>
        </div>
        <input type="button" value="Ví­deos Salvos">
        <input type="button" value="Salas de Aula">
        <input type="button" value="Notificações">
        <input type="button" value="Histórico de Doações">
        <input type="button" value="Reagidos">
        <a href="perfil.jsp">Meu Perfil</a>
        <a href="ajuda.jsp">Ajuda</a>
    </div>
    <c:if test="${not empty textos}">
        <ul>
            <c:forEach var="texto" items="${textos}">
                <div class="caixa-texto">
                    <p><strong>${texto.authorNome}</strong> (${texto.authorType == 'professor' ? 'Professor' : 'Aluno'})</p>
                    <p>${texto.conteudo}</p>
                </div>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty textos}">
        <p>Nenhum texto compartilhado ainda.</p>
    </c:if>
    
    <!-- Se houver erro -->
    <c:if test="${not empty mensagemErro}">
        <p style="color: red;">Erro: ${mensagemErro}</p>
    </c:if>
</body>
</html>
