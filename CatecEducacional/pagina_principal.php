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
        <button onclick="mostrarPopup()">Upload Vídeo</button>
        <div id="meuPopUp" class="popup">
            <div class="conteudo-popup">
                <span class="fechar" onclick="fecharPopup()">&times;</span>
                <p>Qual vídeo você deseja postar?</p>
            </div>
        </div>
        <button onclick="aparecerPopup()">Escrever Texto</button>
        <div id="meusegundoPopUp" class="secondpopup">
            <div class="conteudo-segundopopup">
                <span class="fechardois" onclick="desabrirPopup()">&times;</span>
                <p>O quê você deseja escrever?</p>
                <form action="" method="POST">
                    <textarea id="texto" name="texto" rows="4" cols="50"></textarea><br>
                    <input type="submit" value="Enviar Texto">
                </form>
            </div>
        </div>
        <input type="button" value="Perfil">
        <input type="button" value="Vídeos Salvos">
        <input type="button" value="Salas de Aula">
        <input type="button" value="Notificações">
        <input type="button" value="Histórico de Doações">
        <input type="button" value="Reagidos">
        <a href="ajuda.html">Ajuda</a>
        <?php
            $arquivo = 'texto_salvo.txt';

            if ($_SERVER["REQUEST_METHOD"] == "POST" && !empty($_POST['texto'])) {
                $texto = htmlspecialchars($_POST['texto']);
                $arquivo_atual = fopen($arquivo, "a");
                fwrite($arquivo_atual, $texto . "\n");
                fclose($arquivo_atual);
            }

            if (file_exists($arquivo)) {
                $texto_salvo = file_get_contents($arquivo);
                echo "<pre>$texto_salvo</pre>";
            } 
            
            else {
                echo "<p>Nenhum texto salvo ainda.</p>";
            }
        ?>
    </div>
</body>
</html>
