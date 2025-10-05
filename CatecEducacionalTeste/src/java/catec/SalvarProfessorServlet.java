package catec; // Declara o pacote onde a classe SalvarProfessorServlet está localizada.

import catec.ProfessorDAO; // Importa a classe ProfessorDAO.
import catec.Professor; // Importa a classe Professor.

import jakarta.servlet.ServletException; // Importa a exceção ServletException.
import jakarta.servlet.annotation.WebServlet; // Importa a anotação WebServlet.
import jakarta.servlet.http.HttpServlet; // Importa a classe base HttpServlet.
import jakarta.servlet.http.HttpServletRequest; // Importa a interface HttpServletRequest.
import jakarta.servlet.http.HttpServletResponse; // Importa a interface HttpServletResponse.
import java.io.IOException; // Importa a exceção IOException.
import java.sql.SQLException; // Importa a exceção SQLException.

@WebServlet("/SalvarProfessorServlet") // Anotação que mapeia este servlet para a URL "/SalvarProfessorServlet".
public class SalvarProfessorServlet extends HttpServlet { // Declara a classe SalvarProfessorServlet que estende HttpServlet.
    private static final long serialVersionUID = 1L; // Define um ID de serialização para a classe.

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Sobrescreve o método doPost para lidar com requisições POST.
        request.setCharacterEncoding("UTF-8"); // Define a codificação de caracteres da requisição para UTF-8.

        String nome = request.getParameter("nome"); // Obtém o parâmetro "nome" da requisição.
        String email = request.getParameter("email"); // Obtém o parâmetro "email" da requisição.
        String senha = request.getParameter("senha"); // Obtém o parâmetro "senha" da requisição.
        String areaAtuacao = request.getParameter("areaAtuacao"); // Obtém o parâmetro "areaAtuacao" da requisição.

        Professor professor = new Professor(); // Cria uma nova instância da classe Professor.
        professor.setNome(nome); // Define o nome do professor.
        professor.setEmail(email); // Define o e-mail do professor.
        professor.setSenha(senha); // Lembre-se de fazer hash da senha em produção! // Define a senha do professor (com um lembrete para hash em produção).
        professor.setAreaAtuacao(areaAtuacao); // Define a área de atuação do professor.

        ProfessorDAO professorDAO = new ProfessorDAO(); // Cria uma nova instância de ProfessorDAO.
        try {
            professorDAO.salvarProfessor(professor); // Tenta salvar o professor no banco de dados.
            response.sendRedirect("cadastroSucesso.jsp?tipo=professor"); // Redireciona para uma página de sucesso após o cadastro.
        } catch (SQLException e) { // Captura exceções SQL.
            e.printStackTrace(); // Imprime o stack trace do erro.
            request.setAttribute("mensagemErro", "Erro ao cadastrar professor: " + e.getMessage()); // Define uma mensagem de erro na requisição.
            request.getRequestDispatcher("Como Professor.jsp").forward(request, response); // Encaminha a requisição de volta para a página de cadastro do professor com a mensagem de erro.
        }
    }
}