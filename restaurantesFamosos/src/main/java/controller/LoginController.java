package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.Main;
import componente.Alerta;
import conexao.Conexao;
import conexao.ConexaoMysqlProducao;
import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class LoginController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private TextField tfSenha;

	@FXML
	private Button btnCriarConta;

	@FXML
	private TextField tfLogin;

	@FXML
	private RadioButton rbLembarSenha;

	@FXML
	private Hyperlink linkEsqueciSenha;

	@FXML
	private Button btnEntrar;
	private Conexao log;

	public boolean logado = false;
	
	private UsuarioDAO usuarioDAO;

	public LoginController() {
		usuarioDAO = factory.DAOFactory.get().usuarioDAO();
		log = new ConexaoMysqlProducao();
	}

	@FXML
	void CriarConta(ActionEvent event) {
		AbreTela("CadastraUsuario.fxml");
	}

	@FXML
	void Entrar(ActionEvent event) throws SQLException {

		try {
			Connection connection = log.get();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select Nome, senha from Usuario");
			if (tfLogin.getText().equals("") || (tfSenha.getText().equals(""))) {
				Alerta alerta = new Alerta();
				alerta.nulo();
				return;
			}
			while (rs.next()) {
				if (tfLogin.getText().equals(rs.getString("Nome"))
						&& (tfSenha.getText().equals(rs.getString("senha")))) {
					Alerta alerta1 = new Alerta();
					alerta1.entrar();

					AbreTela("Principal.fxml");
					logado = true;
				}
				return;
			}
			Alerta alerta1 = new Alerta();
			alerta1.incorreto();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void EsqueciSenha(ActionEvent event) {
		AbreTela("EditaPerfil.fxml");
	}

	
	//Metodo de abrir tela que fecha tela antiga e cria nova
	public void AbreTela(String tela) {
		stack.getChildren().clear();
		stack.getChildren().add(getNode(tela));
	}

	public Node getNode(String node) {
		Node no = null;
		try {
			no = FXMLLoader.load(getClass().getResource(Main.PATH_VIEW + node));
		} catch (Exception e) {
		}
		return no;

	}

}
