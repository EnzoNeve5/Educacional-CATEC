package catec; // Declara o pacote onde a classe LoginServlet está localizada.

import catec.AlunoDAO; // Importa a classe AlunoDAO.
import catec.ProfessorDAO; // Importa a classe ProfessorDAO.
import catec.Aluno; // Importa a classe Aluno.
import catec.Professor; // Importa a classe Professor.

import jakarta.servlet.ServletException; // Importa a exceção ServletException.
import jakarta.servlet.annotation.WebServlet; // Importa a anotação WebServlet.
import jakarta.servlet.http.HttpServlet; // Importa a classe base HttpServlet.
import jakarta.servlet.http.HttpServletRequest; // Importa a interface HttpServletRequest.
import jakarta.servlet.http.HttpServletResponse; // Importa a interface HttpServletResponse.
import jakarta.servlet.http.HttpSession; // Importa a interface HttpSession para gerenciar sessões.
import java.io.IOException; // Importa a exceção IOException.
import java.sql.SQLException; // Importa a exceção SQLException.

@WebServlet("/LoginServlet") // Anotação que mapeia este servlet para a URL "/LoginServlet".
public class LoginServlet extends HttpServlet { // Declara a classe LoginServlet que estende HttpServlet.
    private static final long serialVersionUID = 1L; // Define um ID de serialização para a classe.

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Sobrescreve o método doPost para lidar com requisições POST.
        request.setCharacterEncoding("UTF-8"); // Define a codificação de caracteres da requisição para UTF-8.

        String email = request.getParameter("email"); // Obtém o parâmetro "email" da requisição.
        String senha = request.getParameter("senha"); // Obtém o parâmetro "senha" da requisição.
        String tipoUsuario = request.getParameter("tipoUsuario"); // Alterado para "tipoUsuario" para ser mais semântico // Obtém o parâmetro "tipoUsuario" da requisição.

        HttpSession session = request.getSession(); // Obtém a sessão atual (ou cria uma nova se não existir).
        session.invalidate(); // Invalida qualquer sessão anterior // Invalida a sessão atual para garantir uma nova sessão de login.
        session = request.getSession(true); // Cria uma nova sessão // Cria uma nova sessão para o usuário logado.

        try {
            if ("professor".equals(tipoUsuario)) { // Usando "professor" como valor do radio button // Verifica se o tipo de usuário é "professor".
                ProfessorDAO professorDAO = new ProfessorDAO(); // Cria uma nova instância de ProfessorDAO.
                Professor professor = professorDAO.buscarProfessorPorEmail(email); // Busca o professor pelo e-mail.

                // ATENÇÃO: Em produção, a senha fornecida pelo usuário (senha) deve ser hashed
                // e comparada com o hash armazenado no banco de dados (professor.getSenha()).
                // Ex: if (professor != null && BCrypt.checkpw(senha, professor.getSenha()))
                if (professor != null && professor.getSenha().equals(senha)) { // Verifica se o professor foi encontrado e a senha corresponde.
                    session.setAttribute("usuarioLogado", professor); // Armazena o objeto professor na sessão.
                    session.setAttribute("tipoUsuario", "professor"); // Armazena o tipo de usuário na sessão.
                    response.sendRedirect("perfil.jsp"); // Redireciona para a página de perfil.
                } else {
                    request.setAttribute("mensagemErro", "E-mail ou senha de professor inválidos."); // Define uma mensagem de erro na requisição.
                    request.getRequestDispatcher("index.html").forward(request, response); // Encaminha a requisição de volta para a página inicial com a mensagem de erro.
                }
            } else { // Como Aluno (assumindo "aluno" ou padrão) // Se o tipo de usuário não for "professor", assume que é "aluno".
                AlunoDAO alunoDAO = new AlunoDAO(); // Cria uma nova instância de AlunoDAO.
                Aluno aluno = alunoDAO.buscarAlunoPorEmail(email); // Busca o aluno pelo e-mail.

                // ATENÇÃO: Em produção, a senha fornecida pelo usuário (senha) deve ser hashed
                // e comparada com o hash armazenado no banco de dados (aluno.getSenha()).
                // Ex: if (aluno != null && BCrypt.checkpw(senha, aluno.getSenha()))
                if (aluno != null && aluno.getSenha().equals(senha)) { // Verifica se o aluno foi encontrado e a senha corresponde.
                    session.setAttribute("usuarioLogado", aluno); // Armazena o objeto aluno na sessão.
                    session.setAttribute("tipoUsuario", "aluno"); // Armazena o tipo de usuário na sessão.
                    response.sendRedirect("perfil.jsp"); // Redireciona para a página de perfil.
                } else {
                    request.setAttribute("mensagemErro", "E-mail ou senha de aluno inválidos."); // Define uma mensagem de erro na requisição.
                    request.getRequestDispatcher("index.html").forward(request, response); // Encaminha a requisição de volta para a página inicial com a mensagem de erro.
                }
            }
        } catch (SQLException e) { // Captura exceções SQL.
            e.printStackTrace(); // Imprime o stack trace do erro.
            request.setAttribute("mensagemErro", "Erro de banco de dados durante o login: " + e.getMessage()); // Define uma mensagem de erro de banco de dados na requisição.
            request.getRequestDispatcher("index.html").forward(request, response); // Encaminha a requisição de volta para a página inicial com a mensagem de erro.
        }
    }
}