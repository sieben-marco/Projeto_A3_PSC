import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection criarConexao() {
        String usuario = "root";
        String senha = "0561992";
        String base = "a3_teste";

        try {

            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + base, usuario, senha);

            return conexao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
