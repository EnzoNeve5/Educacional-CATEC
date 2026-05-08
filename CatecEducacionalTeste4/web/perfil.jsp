<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="catec.Aluno"%>
<%@page import="catec.Professor"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="catec.css">
    <title>Meu Perfil</title>
</head>
<body>
    <main>
        <h1>Meu Perfil</h1>

        <%
            Object usuarioLogado = session.getAttribute("usuarioLogado");
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");

            if (usuarioLogado == null) {
                // Se não houver usuário logado, redireciona para a página inicial
                response.sendRedirect("index.html");
                return; // Importante para parar a execução do JSP
            }

            if ("aluno".equals(tipoUsuario) && usuarioLogado instanceof Aluno) {
                Aluno aluno = (Aluno) usuarioLogado;
        %>
                <h2>Informações do Aluno</h2>
                <p><strong>Nome:</strong> <%= aluno.getNome() %></p>
                <p><strong>Instituição:</strong> <%= aluno.getInstituicao() %></p>
                <p><strong>E-mail:</strong> <%= aluno.getEmail() %></p>
                <p><strong>Data de Nascimento:</strong> <%= aluno.getDataNascimento() %></p>
                <!-- Exiba outros campos específicos do aluno -->
        <%
            } 

            else if ("professor".equals(tipoUsuario) && usuarioLogado instanceof Professor) {
                Professor professor = (Professor) usuarioLogado;
        %>
                <h2>Informações do Professor</h2>
                <p><strong>Nome:</strong> <%= professor.getNome() %></p>
                <p><strong>Instituição:</strong> <%= professor.getInstituicao() %></p>
                <p><strong>E-mail:</strong> <%= professor.getEmail() %></p>
                <p><strong>Data de Nascimento:</strong> <%= professor.getDataNascimento() %></p>
                <p><strong>Área de Atuação:</strong> <%= professor.getAreaAtuacao() != null ? professor.getAreaAtuacao() : "Não informada" %></p>
                <!-- Exiba outros campos específicos do professor -->
        <%
            } 

            else {
                // Caso haja um erro na sessão ou tipo de usuário
                response.sendRedirect("index.html");
                return;
            }
        %>
        <p><a href="pagina_principal.jsp">Voltar para a Página Principal</a></p>
        <p><a href="LogoutServlet">Sair</a></p>
    </main>
</body>
</html>