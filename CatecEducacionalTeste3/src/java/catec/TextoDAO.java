package catec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO {
    
    // Método existente para salvar texto (deprecated, use o novo)
    public void salvarTexto(String texto) throws SQLException {
        String sql = "INSERT INTO textos (conteudo) VALUES (?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.executeUpdate();
        }
        
        finally {
            if (stmt != null) stmt.close();
            DBConnection.closeConnection(conn);
        }
    }
    
    // Novo método para salvar texto com autor
    public void salvarTexto(String texto, int authorId, String authorType) throws SQLException {
        String sql = "INSERT INTO textos (conteudo, author_id, author_type) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setInt(2, authorId);
            stmt.setString(3, authorType);
            stmt.executeUpdate();
        }
        
        finally {
            if (stmt != null) stmt.close();
            DBConnection.closeConnection(conn);
        }
    }
    
    // Novo método para recuperar todos os textos com autores
    public List<Texto> getAllTextosComAutores() throws SQLException {
        String sql = "SELECT t.id, t.conteudo, t.author_id, t.author_type, " +
                     "CASE WHEN t.author_type = 'professor' THEN p.nome ELSE a.nome END AS author_nome " +
                     "FROM textos t " +
                     "LEFT JOIN professores p ON t.author_type = 'professor' AND t.author_id = p.id " +
                     "LEFT JOIN alunos a ON t.author_type = 'aluno' AND t.author_id = a.id " +
                     "ORDER BY t.id DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Texto> textos = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Texto texto = new Texto();
                texto.setId(rs.getInt("id"));
                texto.setConteudo(rs.getString("conteudo"));
                texto.setAuthorId(rs.getInt("author_id"));
                texto.setAuthorType(rs.getString("author_type"));
                texto.setAuthorNome(rs.getString("author_nome"));
                textos.add(texto);
            }
        }
        
        finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DBConnection.closeConnection(conn);
        }
        
        return textos;
    }
    
    // Método antigo para compatibilidade (deprecated)
    public List<String> getAllTextos() throws SQLException {
        List<Texto> textosComAutores = getAllTextosComAutores();
        List<String> textos = new ArrayList<>();
        for (Texto t : textosComAutores) {
            textos.add(t.getConteudo());
        }
        return textos;
    }
}