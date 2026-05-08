package catec; // Declara o pacote onde a classe LogoutServlet está localizada.

import jakarta.servlet.ServletException; // Importa a exceção ServletException.
import jakarta.servlet.annotation.WebServlet; // Importa a anotação WebServlet.
import jakarta.servlet.http.HttpServlet; // Importa a classe base HttpServlet.
import jakarta.servlet.http.HttpServletRequest; // Importa a interface HttpServletRequest.
import jakarta.servlet.http.HttpServletResponse; // Importa a interface HttpServletResponse.
import jakarta.servlet.http.HttpSession; // Importa a interface HttpSession para gerenciar sessões.
import java.io.IOException; // Importa a exceção IOException.

@WebServlet("/LogoutServlet") // Anotação que mapeia este servlet para a URL "/LogoutServlet".
public class LogoutServlet extends HttpServlet { // Declara a classe LogoutServlet que estende HttpServlet.
    private static final long serialVersionUID = 1L; // Define um ID de serialização para a classe.

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Sobrescreve o método doGet para lidar com requisições GET.
        HttpSession session = request.getSession(false); // Obtém a sessão atual sem criar uma nova se não existir.
        if (session != null) { // Verifica se existe uma sessão ativa.
            session.invalidate(); // Invalida a sessão, removendo todos os atributos e encerrando-a.
        }
        response.sendRedirect("index.html"); // Redireciona o usuário para a página inicial (index.html).
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Sobrescreve o método doPost para lidar com requisições POST.
        doGet(request, response); // Chama o método doGet para processar a requisição POST (comportamento de logout é o mesmo para GET e POST).
    }
}