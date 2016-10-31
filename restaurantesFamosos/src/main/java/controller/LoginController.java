package controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LoginController {

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

	@FXML
	void CriarConta(ActionEvent event) {
		AbreTela("CadastraUsuario.fxml");
	}

	@FXML
	void Entrar(ActionEvent event) throws SQLException {
		if ("admin".equals(tfSenha.getText()) && "admin".equals(tfLogin.getText())) {
			Alert alert = new Alert(AlertType.INFORMATION, "Login efetuado com sucesso!", ButtonType.CLOSE);
			alert.show();
			// abre a tela principal de seta o menu no topo
			AbreTela("Principal.fxml");
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção!! Os dados estão incorretos", ButtonType.CLOSE);
			alert.show();
		}
	}

	@FXML
	void LembrarSenha(ActionEvent event) {

	}

	@FXML
	void EsqueciSenha(ActionEvent event) {
		AbreTela("RecuperaSenha.fxml");
	}

	public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" + tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
	private Conexao log;

	private UsuarioDAO usuarioDAO;

	public LoginController() {
		usuarioDAO = factory.DAOFactory.get().usuarioDAO();
		log = new ConexaoMysqlProducao();

	}

		int compara = 1;
		try {
			Connection connection = log.get();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select Nome, senha from Usuario");
			rs.first();
			while (compara == 1) {
				if (tfLogin.getText().equals("") || (tfSenha.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "Campos não podem ser nulos.");
					compara = 2;
					break;
				}
				if (tfLogin.getText().equals(rs.getString("Nome"))
						&& (tfSenha.getText().equals(rs.getString("Senha")))) {
					JOptionPane.showMessageDialog(null, "Login correto! Aguarde o sistema abrir.");

					AbreTela("Principal.fxml");
					compara = 2;
					break;

				} else {
					JOptionPane.showMessageDialog(null, "Usário ou senha incorretos!");
					compara = 2;
					break;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

