import java.sql.*;
public class ContaDAO {
    Connection conexao;

    public ContaDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    public void listarContas() {

        try {
            String query;
            PreparedStatement statement = null;

            query = "SELECT * FROM conta";
            statement = conexao.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.format("ID\tNOME\t\t\t\t\t\tEMAIL\t\t\t\t\t\t\tENDEREÇO\t\t\t\t\t\tDATA DE NASCIMENTO\n");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                double saldo = rs.getDouble("saldo");

                System.out.format("%d\t%.2f\n", id, saldo);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
    }
}
