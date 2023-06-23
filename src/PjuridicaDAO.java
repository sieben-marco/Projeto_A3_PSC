import java.sql.*;

public class PjuridicaDAO {
	Connection conexao;
	
	//Criando objeto para iniciar a conex�o com o banco de dados.
	public PjuridicaDAO() {
		this.conexao = new Conexao().criarConexao();
	}
	
	// Listagem de Pessoa Jur�dica.
	public void listarPjuridica() {
		
		try {
			String query;
			PreparedStatement stmt = null;
			
			//Vai procurar apenas Clientes com "CNPJ".
			/*if(tipo.equals("TODOS")) {*/
				query = "SELECT * FROM cliente WHERE status = 'inativo'";
				stmt = conexao.prepareStatement(query);
			/*}	else if(nome.equals("BUSCA")){
				//Vai procurar Clientes com o nome inserido no "?".
				query = "SELECT * FROM cliente WHERE nome LIKE ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, "%" + nome + "%");
			}*/
			// Sempre q houver retorno, ir� usar o "ResultSet" para guardar os dados recebidos.
			ResultSet rs = stmt.executeQuery();
			
			//Sistema para ficar melhor quando ocorrer uma busca.
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("ID CLIENTE\tNOME\t\tDOCUMENTO\t\t\tCLIENTE\t\t\tSTATUS");
            System.out.println("--------------------------------------------------------------------------------");
			
			//Enquanto houver linhas para receber dados, vai ocorrer um la�o de repeti��o.
			while (rs.next()) {

                int id1 = rs.getInt("idCliente");
                String nome1 = rs.getString("nome");
                String documento = rs.getString("documento");
                String tipoCliente = rs.getString("tipoCliente");
                String status = rs.getNString("status");

        		//Sistema para combinar com a linha 34, ir� ficar um abaixo do outro para melhorar a visilibidade dos dados.
                System.out.format("%d\t\t\t%-10s\t%-10s\t\t%-5s\t\t%-10s\n\n", id1, nome1, documento, tipoCliente, status);

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

    public boolean realizarLogin(String documento, String senha) {

        try {
            String query;
            PreparedStatement statement = null;

            query = "SELECT documento, senha, status FROM cliente WHERE documento = ? and senha = ?";
            statement = conexao.prepareStatement(query);

            statement.setString(1, documento);
            statement.setString(2, senha);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String doc = rs.getString("documento");
                String acesso = rs.getString("senha");
                String status = rs.getString("status");

                if (documento.equals(doc) && senha.equals(acesso) && status.equals("ativo")) {
                    return true;
                }
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }

        return false;
    }

    public int obterIdCliente(String documento) {

        int id = 0;

        try {
            String query;
            PreparedStatement statement = null;

            query = "SELECT idCliente FROM cliente WHERE documento = ?";
            statement = conexao.prepareStatement(query);

            statement.setString(1, documento);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("idCliente");
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }

        return id;
    }
	
	//M�todos para Cadastrar um novo Cliente.
	public void realizarCadastro(Pjuridica pJuridica, String tipoCliente) {
		try {
			// Preparando a estrutura para a execu��o no banco de dados.
            String query = "INSERT INTO cliente(nome, documento, tipoCliente, senha) VALUES (?,?,?,?)";
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

    public void encerrarConta(int id) {

        try {
            String query = "UPDATE cliente SET status = 'inativo' WHERE idCliente = ?";
            PreparedStatement statement = conexao.prepareStatement(query);
            statement.setInt(1, id);

            statement.execute();
            statement.close();

            System.out.println("Conta Encerrada!");

        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
    }

    public void excluirCliente(int id) {
    	
    	try {
            // Metodo para a exclus�o do Cliente.
            String query = "DELETE FROM cliente WHERE idCliente = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);

            stmt.setInt(1, id);
            
            int res = stmt.executeUpdate();
            
            if(res > 0)
                System.out.println("Exclusão da conta concluída!");
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
