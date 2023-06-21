import java.sql.*;

public class ClienteDAO {
	Connection Conexao;
	
	public ClienteDAO() {
		this.Conexao = new Conexao().criarConexao();
	}
	
	public void listarCliente (String tipo, String termo) {
		
		try {
			String query;
			PreparedStatement stmt = null;
			
			if(tipo.equals("TODOS")) {
                query = "SELECT * FROM cliente";
                stmt = Conexao.prepareStatement(query);
			} else if(tipo.equals("BUSCA")){
                query = "SELECT * FROM cliente WHERE nome LIKE ?";
                stmt = Conexao.prepareStatement(query);
                stmt.setString(1, "%" + termo + "%");
            }
			
			ResultSet rs = stmt.executeQuery();
			
			System.out.println("----------------------------------------------------------------------------------------------");
            System.out.format("ID\tNOME\t\t\t\t\t\tDOCUMENTO\t\t\t\t\t\t\ttIPO DO CLIENTE\n");
            System.out.println("----------------------------------------------------------------------------------------------");
			
            while (rs.next()) {
            	
            	int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String documento = rs.getString("documento");
                String tipoCliente = rs.getString("tipoCliente");
                
                System.out.format("%d\t%-20s\t\t%-30s\t%-30s\n", id, nome, documento, tipoCliente);
            }
            
            rs.close();
            stmt.close();
            
		  } catch (SQLException e) {
	            // Em caso de ocorrer erro na integração com banco de dados, as informações do erro serão exibidas
	            System.out.println("Error Code = " + e.getErrorCode());
	            System.out.println("SQL state = " + e.getSQLState());
	            System.out.println("Message = " + e.getMessage());
	      }	
		}
}
