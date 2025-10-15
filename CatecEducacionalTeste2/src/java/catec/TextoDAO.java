package catec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO {
    
    // Método existente para salvar texto
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
    
    // Novo método para recuperar todos os textos
    public List<String> getAllTextos() throws SQLException {
        String sql = "SELECT conteudo FROM textos";  // Assuma a tabela 'textos' com coluna 'conteudo'
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> textos = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                textos.add(rs.getString("conteudo"));  // Adiciona cada texto à lista
            }
        }
        
        finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            DBConnection.closeConnection(conn);
        }
        
        return textos;
    }
}