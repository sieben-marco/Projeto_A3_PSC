import java.sql.*;
import java.time.LocalDate;

public class TransacaoDAO {
    Connection conexao;

    // construtor que cria um objeto e inicia a conexão com o banco de dados
    public TransacaoDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    // insere dados na tabela transacao
    public boolean registrar(Transacao transacao) {
        try {
                // prepara sql para execucao no banco de dados
                String query = "insert into transacao(valor, tipoTransacao, data, idCliente, idConta) values (?,?,?,?,?)";
                PreparedStatement statement = conexao.prepareStatement(query);

                statement.setDouble(1, transacao.getValor());
                statement.setString(2, transacao.getTipo());
                statement.setDate(3, Date.valueOf(transacao.getData()));
                statement.setInt(4, 1);
                statement.setInt(5, 1);

                statement.execute();
                statement.close();

                System.out.println("\nTransação cadastrada!\n");
                return true;

        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }

        return false;
    }

    public void consultar(/*int idCliente, int idConta*/) {

        try {
            String query;
            PreparedStatement statement = null;

            query = "select * from transacao";
            statement = conexao.prepareStatement(query);
            //statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            System.out.println("\n----------------------------------------------------------------------------");
            System.out.format("ID\t\tVALOR\t\tTRANSAÇÃO\t\tDATA\t\t\tID CLIENTE\t\tID CONTA\n");
            System.out.println("----------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("idTransacao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipoTransacao");
                LocalDate data = rs.getDate("data").toLocalDate();
                int idCliente = rs.getInt("idCliente");
                int idConta = rs.getInt("idConta");


                if (tipo.equals("deposito")) {
                    System.out.format("%d\t\t%.2f\t\t%-5s\t\t%-10s\t\t%d\t\t\t\t%d\n", id, valor, tipo, data, idCliente, idConta);
                } else {
                    System.out.format("%d\t\t%.2f\t\t%-5s\t\t\t%-10s\t\t%d\t\t\t\t%d\n", id, valor, tipo, data, idCliente, idConta);
                }
            }

            System.out.println();

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
    }
}
