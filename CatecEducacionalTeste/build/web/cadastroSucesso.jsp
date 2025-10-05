<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="catec.css">
    <title>Cadastro Realizado</title>
</head>
<body>
    <main>
        <h1>Cadastro Realizado com Sucesso!</h1>
        <p>Seu cadastro como <%= request.getParameter("tipo") %> foi concluído.</p>
        <p><a href="index.html">Voltar para a Página Inicial</a></p>
        <p><a href="pagina_principal.jsp">Ir para a Página Principal</a></p>
    </main>
</body>
</html>