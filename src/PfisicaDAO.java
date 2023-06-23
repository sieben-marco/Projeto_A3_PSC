import java.sql.*;

public class PfisicaDAO {
    Connection conexao;

    public PfisicaDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    public void listarPfisica() {
        try {
            String query;
            PreparedStatement stmt = null;
            /*if (id.equals("TODOS")) {*/
                query = "SELECT * FROM cliente WHERE status = 'inativo'";
                stmt = conexao.prepareStatement(query);
            /*} else if (id.equals("BUSCA")) {
                query = "SELECT * FROM cliente WHERE cpf LIKE ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, "%" + nome + "%");

            }*/
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("ID CLIENTE\tNOME\t\tDOCUMENTO\t\t\tCLIENTE\t\t\tSTATUS");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int id1 = rs.getInt("idCliente");
                String nome1 = rs.getNString("nome");
                String documento = rs.getNString("documento");
                String tipoCliente = rs.getNString("tipoCliente");
                String status = rs.getNString("status");

                System.out.format("%d\t\t\t%-10s\t%-10s\t\t%-5s\t\t%-10s\n\n", id1, nome1, documento, tipoCliente, status);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            // em caso de ocorrera algum erro ao integrar as classes com
            // o banco de dados sera dado os seguintes avisos
            System.out.println("Error code=" + e.getErrorCode());
            System.out.println("SQL state= " + e.getSQLState());
            System.out.println("Message=" + e.getMessage());
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


    public void realizarCadastro(Pfisica pFisica, String tipoCliente) {
        try{
            String query = "INSERT INTO cliente(nome, documento, tipoCliente, senha) VALUES(?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1,pFisica.getNome());
            stmt.setString(2,pFisica.getCpf());
            stmt.setString(3,tipoCliente);
            stmt.setString(4,pFisica.getSenha());

            stmt.execute();
            stmt.close();

            System.out.println("Cliente cadastrado!");
        } catch (SQLException e){

            System.out.println("Error Code ="+e.getErrorCode());
            System.out.println("SQL state ="+e.getSQLState());
            System.out.println("Message = "+e.getMessage());

        }

    }

    public void editarCliente(int id,int campo, String valor){

        String nomecam= switch (campo){
            case 1 -> "nome";
            case 2 -> "documento";
            default -> "";
        };
        try{
            String query = "UPDATE cliente SET"+nomecam+"= ? WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);

            stmt.setString(1,valor);
            stmt.setInt(2,id);

            stmt.execute();
            stmt.close();

            System.out.println("Cliente editado!");
    } catch (SQLException e){

            System.out.println("Error code="+ e.getErrorCode());
            System.out.println("SQL state="+e.getSQLState());
            System.out.println("Message"+e.getMessage());
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
    public void excluirCliente(int id){

            try {
                String query = "DELETE FROM cliente WHERE idCliente = ?";
                PreparedStatement stmt = conexao.prepareStatement(query);

                stmt.setInt(1, id);
                int res = stmt.executeUpdate();
                if (res > 0)
                    System.out.println("Exclusão da conta concluída!");
                else
                    System.out.println("Esse id de cliente nao existe");
                stmt.close();
            } catch (SQLException e) {

                System.out.println("Error Code= " + e.getErrorCode());
                System.out.println("SQL state= " + e.getSQLState());
                System.out.println("Message= " + e.getMessage());
            }

    }
}
