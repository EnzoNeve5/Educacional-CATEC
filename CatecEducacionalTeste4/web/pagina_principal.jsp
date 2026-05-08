<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (request.getAttribute("textos") == null) {
        try {
            catec.TextoDAO textoDAO = new catec.TextoDAO();
            java.util.List<catec.Texto> textos = textoDAO.getAllTextosComAutores();
            request.setAttribute("textos", textos);
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao carregar textos: " + e.getMessage());
        }
    }
%>
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
        .popup, .secondpopup, .author-popup {
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
        .popup .fechar, .secondpopup .fechardois, .author-popup .fechar {
            cursor: pointer;
            float: right;
            font-size: 20px;
            font-weight: bold;
        }
        .author-link {
            background: #ccc;
            color: blue;
            cursor: pointer;
            text-decoration: none;
            font-size: 24px;
        }
        .author-popup p {
            margin: 8px 0;
        }
        .popup .fechar:hover, .secondpopup .fechardois:hover {
            color: red;
        }
        p {
            font-size: 24px;
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

        function mostrarAutorDetalhes(element) {
            var nome = element.dataset.nome;
            var instituicao = element.dataset.instituicao;
            var email = element.dataset.email;
            var nascimento = element.dataset.nascimento;
            var area = element.dataset.area;
            var tipo = element.dataset.type;

            document.getElementById('popupAutorNome').textContent = nome || 'Não informado';
            document.getElementById('popupAutorInstituicao').textContent = instituicao || 'Não informada';
            document.getElementById('popupAutorEmail').textContent = email || 'Não informado';
            document.getElementById('popupAutorNascimento').textContent = nascimento || 'Não informada';
            document.getElementById('popupAutorArea').textContent = (tipo === 'professor' ? (area || 'Não informada') : '');
            document.getElementById('popupAutorAreaContainer').style.display = tipo === 'professor' ? 'block' : 'none';
            document.getElementById('authorPopup').style.display = 'block';
        }

        function fecharPopupAutor() {
            document.getElementById('authorPopup').style.display = 'none';
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
                <input type="submit" value="Postar Vídeo">
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
        <div id="authorPopup" class="author-popup">
            <span class="fechar" onclick="fecharPopupAutor()">&times;</span>
            <h3>Informações do Autor</h3>
            <p><strong>Nome:</strong> <span id="popupAutorNome"></span></p>
            <p><strong>Instituição:</strong> <span id="popupAutorInstituicao"></span></p>
            <p><strong>E-mail:</strong> <span id="popupAutorEmail"></span></p>
            <p><strong>Data de Nascimento:</strong> <span id="popupAutorNascimento"></span></p>
            <p id="popupAutorAreaContainer"><strong>Área de Atuação:</strong> <span id="popupAutorArea"></span></p>
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
                    <p><strong><a href="javascript:void(0);" class="author-link"
                          data-nome="${texto.authorNome}"
                          data-instituicao="${texto.authorInstituicao}"
                          data-email="${texto.authorEmail}"
                          data-nascimento="${texto.authorDataNascimento}"
                          data-area="${texto.authorAreaAtuacao}"
                          data-type="${texto.authorType}"
                          onclick="mostrarAutorDetalhes(this)">${texto.authorNome}</a></strong>
                        (${texto.authorType == 'professor' ? 'Professor' : 'Aluno'})</p>
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
