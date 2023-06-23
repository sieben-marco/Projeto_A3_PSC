import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransacaoDAO {
    Connection conexao;

    // construtor que cria um objeto e inicia a conexão com o banco de dados
    public TransacaoDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    // insere dados na tabela transacao
    public boolean registrar(Transacao transacao, int id) {
        try {
                // prepara sql para execucao no banco de dados
                String query = "insert into transacao(valor, tipoTransacao, data, idCliente, idConta) values (?,?,?,?,?)";
                PreparedStatement statement = conexao.prepareStatement(query);

                statement.setDouble(1, transacao.getValor());
                statement.setString(2, transacao.getTipo());
                statement.setDate(3, Date.valueOf(transacao.getData()));
                statement.setInt(4, id);
                statement.setInt(5, id);

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

    public void consultar(int id/*int idCliente, int idConta*/) {

        try {
            String query;
            PreparedStatement statement = null;

            query = "select idTransacao, valor, tipoTransacao, data, C.nome, idConta from transacao as T inner join cliente as C on T.idCliente = C.idCliente where T.idCliente = ?";
            statement = conexao.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.format("ID TRANSAÇÃO\t\tVALOR\t\tTRANSAÇÃO\t\tDATA\t\t\tNOME\t\t\t\tID CONTA\n");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                int idT = rs.getInt("idTransacao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipoTransacao");
                LocalDate data = rs.getDate("data").toLocalDate();
                String dataBR = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String nome = rs.getString("nome");
                int idConta = rs.getInt("idConta");


                if (tipo.equals("deposito")) {
                    System.out.format("%d\t\t\t\t\t%.2f\t\t%-5s\t\t%-10s\t\t%-5s\t\t\t\t%d\n", idT, valor, tipo, dataBR, nome, idConta);
                } else {
                    System.out.format("%d\t\t\t\t\t%.2f\t\t%-5s\t\t\t%-10s\t\t%-5s\t\t\t\t%d\n", idT, valor, tipo, dataBR, nome, idConta);
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

    public boolean excluirTrans(int id) {

        try {
            String query = "DELETE FROM transacao WHERE idCliente = ?";
            PreparedStatement statement = conexao.prepareStatement(query);
            statement.setInt(1, id);

            int res = statement.executeUpdate();

            if(res > 0) {
                return true;
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
        return false;
    }
}
