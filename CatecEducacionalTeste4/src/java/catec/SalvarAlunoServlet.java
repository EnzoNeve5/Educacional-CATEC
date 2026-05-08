package catec; // Declara o pacote onde a classe SalvarAlunoServlet está localizada.

import catec.AlunoDAO; // Importa a classe AlunoDAO.
import catec.Aluno; // Importa a classe Aluno.

import jakarta.servlet.ServletException; // Importa a exceção ServletException.
import jakarta.servlet.annotation.WebServlet; // Importa a anotação WebServlet.
import jakarta.servlet.http.HttpServlet; // Importa a classe base HttpServlet.
import jakarta.servlet.http.HttpServletRequest; // Importa a interface HttpServletRequest.
import jakarta.servlet.http.HttpServletResponse; // Importa a interface HttpServletResponse.
import java.io.IOException; // Importa a exceção IOException.
import java.sql.SQLException; // Importa a exceção SQLException.
import java.time.LocalDate; // Importa a classe LocalDate para lidar com datas.
import java.time.format.DateTimeParseException; // Importa a exceção DateTimeParseException para erros de formato de data.

@WebServlet("/SalvarAlunoServlet") // Anotação que mapeia este servlet para a URL "/SalvarAlunoServlet".
public class SalvarAlunoServlet extends HttpServlet { // Declara a classe SalvarAlunoServlet que estende HttpServlet.
    private static final long serialVersionUID = 1L; // Define um ID de serialização para a classe.

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Sobrescreve o método doPost para lidar com requisições POST.
        request.setCharacterEncoding("UTF-8"); // Para lidar com caracteres especiais // Define a codificação de caracteres da requisição para UTF-8.

        String nome = request.getParameter("nome"); // Obtém o parâmetro "nome" da requisição.
        String instituicao = request.getParameter("instituicao"); // Obtém o parâmetro "instituicao" da requisição.
        String email = request.getParameter("email"); // Obtém o parâmetro "email" da requisição.
        String senha = request.getParameter("senha"); // Obtém o parâmetro "senha" da requisição.
        String dataNascimentoStr = request.getParameter("dataNascimento");  // Pode ser null ou vazio // Obtém o parâmetro "dataNascimento" da requisição.
         
        LocalDate dataNascimento = null; // Inicializa a variável dataNascimento como nula.
        if (dataNascimentoStr != null && !dataNascimentoStr.trim().isEmpty()) { // Verifica se a string da data de nascimento não é nula e não está vazia.
            try {
                dataNascimento = LocalDate.parse(dataNascimentoStr);  // Parse só se não for null/vazio // Tenta converter a string da data para um objeto LocalDate.
            }
            
            catch (DateTimeParseException e) { // Captura exceções de formato de data inválido.
                // Trate erro de formato inválido (ex: data não no formato YYYY-MM-DD)
                request.setAttribute("erro", "Data de nascimento inválida. Use o formato YYYY-MM-DD."); // Define uma mensagem de erro na requisição.
                request.getRequestDispatcher("cadastro_aluno.html").forward(request, response); // Encaminha a requisição de volta para a página de cadastro do aluno com a mensagem de erro.
                return; // Sai do método.
            }
        }
        // Se dataNascimentoStr for null ou vazio, dataNascimento permanece null (opcional)
         
        Aluno aluno = new Aluno(nome, instituicao, email, senha, dataNascimento);  // Passe null se não preenchido // Cria uma nova instância da classe Aluno com os dados obtidos.
        
        AlunoDAO alunoDAO = new AlunoDAO(); // Cria uma nova instância de AlunoDAO.
        boolean sucesso = alunoDAO.salvarAluno(aluno); // Tenta salvar o aluno no banco de dados e armazena o resultado.
        
        if (sucesso) { // Se o aluno foi salvo com sucesso.
             response.sendRedirect("cadastroSucesso.jsp?tipo=aluno");  // Ou página de sucesso // Redireciona para uma página de sucesso.
        }
        
        else { // Se houve um erro ao salvar o aluno.
             request.setAttribute("erro", "Erro ao salvar aluno."); // Define uma mensagem de erro na requisição.
             request.getRequestDispatcher("cadastro_aluno.html").forward(request, response); // Encaminha a requisição de volta para a página de cadastro do aluno com a mensagem de erro.
        }
    }
}