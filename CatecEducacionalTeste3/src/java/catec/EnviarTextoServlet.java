package catec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EnviarTextoServlet")
public class EnviarTextoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // Para lidar com caracteres especiais
        
        String texto = request.getParameter("texto");  // Obtém o texto do formulário
        
        if (texto != null && !texto.isEmpty()) {
            try {
                // Obter usuário da sessão
                HttpSession session = request.getSession();
                Object usuarioLogado = session.getAttribute("usuarioLogado");
                String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                
                if (usuarioLogado == null) {
                    request.setAttribute("mensagemErro", "Você precisa estar logado para postar um texto.");
                    request.getRequestDispatcher("pagina_principal.jsp").forward(request, response);
                    return;
                }
                
                int authorId;
                if ("professor".equals(tipoUsuario)) {
                    Professor professor = (Professor) usuarioLogado;
                    authorId = professor.getId();
                } else {
                    Aluno aluno = (Aluno) usuarioLogado;
                    authorId = aluno.getId();
                }
                
                // Salvar o texto no banco de dados usando o DAO
                TextoDAO textoDAO = new TextoDAO();
                textoDAO.salvarTexto(texto, authorId, tipoUsuario);
                
                // Redireciona para a página de exibição
                response.sendRedirect("ExibirTextosServlet");
            }
            
            catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensagemErro", "Erro ao salvar texto: " + e.getMessage());
                request.getRequestDispatcher("pagina_principal.jsp").forward(request, response);
            }
        }
        
        else {
            request.setAttribute("mensagemErro", "Texto não pode ser vazio.");
            request.getRequestDispatcher("pagina_principal.jsp").forward(request, response);
        }
    }
}