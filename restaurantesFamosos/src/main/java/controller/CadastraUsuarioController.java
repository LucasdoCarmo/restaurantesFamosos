package controller;

import application.Main;
import dao.UsuarioDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Usuario;

public class CadastraUsuarioController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private TextField tfSenha;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfConfirmaSenha;

	@FXML
	private Button btnFinaliza;

	@FXML
	private TextField tfNome;

	// @FXML
	// private TextField tfEmail;

	// @FXML
	// private TextField tfConfirmaEmail;

	private UsuarioDAO usuarioDAO;

	public CadastraUsuarioController() {
		usuarioDAO = DAOFactory.get().usuarioDAO();
	}

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Login.fxml");
	}

	@FXML
	void FinalizaCadastro(ActionEvent event) {
		Usuario usuario = novoUsuario();
		usuarioDAO.salvar(usuario);
		AbreTela("Login.fxml");
	}

	private Usuario novoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(tfNome.getText());

		if (tfSenha.getText() != tfConfirmaSenha.getText()) {
			usuario.setSenha(tfSenha.getText());
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção! As senhas não são iguais", ButtonType.CLOSE);
			alert.show();
		}
		return usuario;
	}

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
