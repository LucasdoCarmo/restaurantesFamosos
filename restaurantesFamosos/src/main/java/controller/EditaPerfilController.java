package controller;

import application.Main;
import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Usuario;

public class EditaPerfilController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private Button btnConfirmar;

	@FXML
	private TextField tfCodigo;

	@FXML
	private TextField tfSenha;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfConfirmaSenha;

	@FXML
	private TextField tfNome;

	private UsuarioDAO usuarioDAO;

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Principal.fxml");
	}

	@FXML
	void Confirmar(ActionEvent event) {
		Usuario usuario = modificaUsuario();
		usuarioDAO.salvar(usuario);
		AbreTela("Principal.fxml");
	}

	private Usuario modificaUsuario() {
		// implementar uma forma de salvar a avaliação do usuário
		usuarioDAO.excluir(Long.valueOf(tfCodigo.getText()));
		Usuario usuario = new Usuario();
		usuario.setNome(tfNome.getText());
		usuario.setEmail(tfEmail.getText());
		if (tfConfirmaSenha.getText() == tfSenha.getText()) {
			if (!tfSenha.getText().isEmpty())
				usuario.setSenha(tfSenha.getText());
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
