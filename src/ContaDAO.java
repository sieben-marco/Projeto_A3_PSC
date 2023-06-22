import java.sql.*;
public class ContaDAO {
    Connection conexao;

    public ContaDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    public void abrirConta(Conta conta) {

        try {
            String query = "INSERT INTO conta(saldo, idCliente) VALUES (?,?)";
            PreparedStatement statement = conexao.prepareStatement(query);

            statement.setDouble(1, conta.getSaldo());
            statement.setInt(2, conta.getIdCliente());

            statement.execute();
            statement.close();

            System.out.println("Conta criada!");

        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
    }

    public void depositar(double valor) {

        try {
            String query = "UPDATE conta SET saldo = (saldo + ?) WHERE idConta = ?";

        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
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
