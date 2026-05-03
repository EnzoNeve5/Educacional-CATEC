package catec;

import catec.Professor;
import catec.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException; // Importa a exceção SQLException.
import java.time.LocalDate; // Já está no seu código, mas confirme.
import java.time.format.DateTimeParseException; // Adicione esta importação para tratar exceções de parsing.

@WebServlet("/SalvarProfessorServlet")
public class SalvarProfessorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String instituicao = request.getParameter("instituicao");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String dataNascimentoStr = request.getParameter("dataNascimento");  // Obtém como String
        String areaAtuacao = request.getParameter("areaAtuacao");

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setInstituicao(instituicao);
        professor.setEmail(email);
        professor.setSenha(senha);  // Lembre-se de hashear a senha em produção!
        professor.setAreaAtuacao(areaAtuacao);

        // Agora, converta a String para LocalDate
        LocalDate dataNascimento = null;
        try {
            if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
                dataNascimento = LocalDate.parse(dataNascimentoStr);  // Converte a String para LocalDate
                professor.setDataNascimento(dataNascimento);  // Define no objeto Professor
            }
            
            else {
                // Se a data não foi fornecida, trate como erro ou defina um valor padrão (se aplicável)
                throw new IllegalArgumentException("Data de nascimento é obrigatória.");
            }
        }
        
        catch (DateTimeParseException e) {
            // Erro: A String não está no formato correto
            request.setAttribute("mensagemErro", "Formato de data inválido. Use o formato yyyy-MM-dd, por exemplo: 1990-01-01.");
            request.getRequestDispatcher("Como Professor.jsp").forward(request, response);
            return;  // Sai do método para não prosseguir
        }
        
        catch (IllegalArgumentException e) {
            // Erro: Data não fornecida
            request.setAttribute("mensagemErro", e.getMessage());
            request.getRequestDispatcher("Como Professor.jsp").forward(request, response);
            return;
        }

        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            professorDAO.salvarProfessor(professor);
            response.sendRedirect("cadastroSucesso.jsp?tipo=professor");
        }
        
        catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao cadastrar professor: " + e.getMessage());
            request.getRequestDispatcher("Como Professor.jsp").forward(request, response);
        }
    }
}