import java.sql.*;

public class PjuridicaDAO {
	Connection conexao;
	
	//Criando objeto para iniciar a conex�o com o banco de dados.
	public PjuridicaDAO() {
		this.conexao = new Conexao().criarConexao();
	}
	
	// Listagem de Pessoa Jur�dica.
	public void listarPjuridica(int id, String nome, String tipo) {
		
		try {
			String query;
			PreparedStatement stmt = null;
			
			//Vai procurar apenas Clientes com "CNPJ".
			if(tipo.equals("TODOS")) {
				query = "SELECT * FROM cliente WHERE tipoCliente = 'cnpj'";
				stmt = conexao.prepareStatement(query);
			}	else if(nome.equals("BUSCA")){
				//Vai procurar Clientes com o nome inserido no "?".
				query = "SELECT * FROM cliente WHERE nome LIKE ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, "%" + nome + "%");
			}
			// Sempre q houver retorno, ir� usar o "ResultSet" para guardar os dados recebidos.
			ResultSet rs = stmt.executeQuery();
			
			//Sistema para ficar melhor quando ocorrer uma busca.
			System.out.println("ID\\tNOME\\t\\t\\t\\t\\t\\tDOCUMENTO\\t\\t\\t\\t\\t\\t\\tPESSOA JUR�DICA");
			
			//Enquanto houver linhas para receber dados, vai ocorrer um la�o de repeti��o.
			while (rs.next()) {

                int id1 = rs.getInt("idCliente");
                String nome1 = rs.getString("nome");
                String cnpj = rs.getString("documento");
                String tipoCliente = rs.getString("tipoCliente");
        		//Sistema para combinar com a linha 34, ir� ficar um abaixo do outro para melhorar a visilibidade dos dados.
                System.out.format("%d\t%-20s\t\t%-30s\t%-30s\n", id1, nome1, cnpj, tipoCliente);

			}
			
			rs.close();
            stmt.close();
            
		   } catch (SQLException e) {
	    		// Se houver um erro, ser� exibido.
	            System.out.println("Error Code = " + e.getErrorCode());
	            System.out.println("SQL state = " + e.getSQLState());
	            System.out.println("Message = " + e.getMessage());
				
		}
	}
	
	//M�todos para Cadastrar um novo Cliente.
	public void realizarCadastro(Pjuridica pJuridica, String tipoCliente) {
		try {
			// Preparando a estrutura para a execu��o no banco de dados.
            String query = "INSERT INTO cliente (nome, documento, tipoCliente, senha) VALUES (?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            
            stmt.setString(1, pJuridica.getNome());
            stmt.setString(2, pJuridica.getCnpj());
            stmt.setString(3, tipoCliente);
            stmt.setString(4, pJuridica.getSenha());

            stmt.execute();
            stmt.close();
            
            System.out.println("Cliente cadastrado!");
            
		} catch (SQLException e) {
    		// Se houver um erro, ser� exibido.
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
		}
	}
    // M�todo para editar o Cliente.        
    public void editarCliente(int id, int campo, String valor) {
    	
    	//Escolher a op��o que quer editar.
    	 String nomecampo = switch (campo) {
         case 1 -> "nome";
         case 2 -> "documento";
         default -> ""; 
    };
    
    try {
        String query = "UPDATE cliente SET " + nomecampo +  " = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(query);
        
        stmt.setString(1, valor);
        stmt.setInt(2, id);
        
        stmt.execute();
        stmt.close();
        
        System.out.println("Cliente editado!");
        
    } catch (SQLException e) {
        // Se acontecer algum erro, ser� exibido.
        System.out.println("Error Code = " + e.getErrorCode());
        System.out.println("SQL state = " + e.getSQLState());
        System.out.println("Message = " + e.getMessage()); 
        }
        
    }

    public void excluirCliente(int id) {
    	
    	try {
            // M�todo para a exclus�o do Cliente.
            String query = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);

            stmt.setInt(1, id);
            
            int res = stmt.executeUpdate();
            
            if(res > 0)
                System.out.println("Exclus�o do Cliente feita!");
            else
                System.out.println("Esse ID do cliente nao existe");

            stmt.close();
            
    	} catch (SQLException e) {
    		// Se houver um erro, ser� exibido.
    		System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
    	}
    	
    

	}
	
	
	
}
