package catec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
                // Exemplo: Salvar o texto no banco de dados usando um DAO
                // Assuma que você tem um TextoDAO para isso
                TextoDAO textoDAO = new TextoDAO();
                textoDAO.salvarTexto(texto);  // Método hipotético para salvar o texto
                
                // Redireciona para uma página de sucesso
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