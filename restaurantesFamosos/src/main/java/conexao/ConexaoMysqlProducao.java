package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysqlProducao implements Conexao {

	private static Connection connection;

	static {
		createConnection();
	}

	private static void createConnection() {
		String url = "jdbc:mysql://localhost:3306/restaurantes2";
		String user = "root";
		String password = "Eagle15";
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("conectado");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Erro de conex„o com o banco de dados");
		}
	}

	public Connection get() {
		try {
			if (connection.isClosed()) {
				createConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar conex√£o");
		}
	}

}
