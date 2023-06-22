import java.sql.*;

public class PfisicaDAO {
    Connection conexao;

    public PfisicaDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    public void listarPfisica(String id, String nome) {
        try {
            String query;
            PreparedStatement stmt = null;
            if (id.equals("TODOS")) {
                query = "SELECT * FROM cliente WHERE tipocliente = 'cpf'";
                stmt = conexao.prepareStatement(query);
            } else if (id.equals("BUSCA")) {
                query = "SELECT * FROM cliente WHERE cpf LIKE ?";
                stmt = conexao.prepareStatement(query);
                stmt.setString(1, "%" + nome + "%");

            }
            ResultSet rs = stmt.executeQuery();
            System.out.println("ID\tNOME\t\t\t\t\t\tDOCUMENTO\t\t\t\t\t\t\tPESSOA FISICA\t\t\t\t\t\t\n");
            while (rs.next()) {
                int id1 = rs.getInt("idCliente");
                String nome1 = rs.getNString("nome");
                String cpf = rs.getNString("documento");
                String tipocliente = rs.getNString("tipoCliente");

                System.out.println("%d\\t%-20s\\t\\t%-30s\\t%-30s\\t%-10s\\n"+id1+nome1+cpf+tipocliente);
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

            query = "SELECT documento, senha FROM cliente WHERE documento = ? and senha = ?";
            statement = conexao.prepareStatement(query);

            statement.setString(1, documento);
            statement.setString(2, senha);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String doc = rs.getString("documento");
                String acesso = rs.getString("senha");

                if (documento.equals(doc) && senha.equals(acesso)) {
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
    public void excluirCliente(int id){
        try{
            String query = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);

            stmt.setInt(1,id);
            int res = stmt.executeUpdate();
            if (res>0)
                System.out.println("Exclusao do cliente feita!");
            else
                System.out.println("Esse id de cliente nao existe");
            stmt.close();
        }catch (SQLException e){

            System.out.println("Error Code= "+ e.getErrorCode());
            System.out.println("SQL state= "+ e.getSQLState());
            System.out.println("Message= "+ e.getMessage());
        }
    }
}
