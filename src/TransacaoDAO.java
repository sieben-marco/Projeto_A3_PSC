import java.sql.*;

public class TransacaoDAO {
    Connection conexao;

    // construtor que cria um objeto e inicia a conexão com o banco de dados
    public TransacaoDAO() {
        this.conexao = new Conexao().criarConexao();
    }

    // insere dados na tabela transacao
    public void registrar(Transacao transacao) {
        try {
            //if (transacao.getTipo() == 1) {
                // prepara sql para execucao no banco de dados
                String query = "insert into transacao(valor, tipoTransacao, data) values (?,?,?)";
                PreparedStatement statement = conexao.prepareStatement(query);

                statement.setDouble(1, transacao.getValor());
                statement.setString(2, transacao.getTipo());
                statement.setDate(3, Date.valueOf(transacao.getData()));

                statement.execute();
                statement.close();

                System.out.println("Nova transacao cadastrado!");
            /*} else if (transacao.getTipo() == 2) {
                String query = "insert into transacao(valor, tipo, data) values (?,?,?)";
                PreparedStatement statement = conexao.prepareStatement(query);

                statement.setDouble(1, transacao.getValor());
                statement.setString(2, "cnpj");
                statement.setDate(3, Date.valueOf(transacao.getData()));

                statement.execute();
                statement.close();
            }*/
        } catch (SQLException e) {
            System.out.println("Error Code = " + e.getErrorCode());
            System.out.println("SQL state = " + e.getSQLState());
            System.out.println("Message = " + e.getMessage());
        }
    }
}
