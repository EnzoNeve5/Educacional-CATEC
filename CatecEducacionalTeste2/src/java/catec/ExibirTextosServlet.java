package catec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ExibirTextosServlet")
public class ExibirTextosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TextoDAO textoDAO = new TextoDAO();
            List<String> textos = textoDAO.getAllTextos();  // Busca todos os textos
            
            // Passa a lista para a request
            request.setAttribute("textos", textos);
            
            // Encaminha para a JSP
            request.getRequestDispatcher("pagina_principal.jsp").forward(request, response);
        }
        
        catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao recuperar textos: " + e.getMessage());
            request.getRequestDispatcher("pagina_principal.jsp").forward(request, response);
        }
    }
}