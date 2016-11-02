package controller;

import java.io.IOException;
import application.Main;
import dao.UsuarioDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Usuario;

public class CadastraUsuarioController {

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
	
	//@FXML
	//private TextField tfEmail;
	
	//@FXML
	//private TextField tfConfirmaEmail;

	private UsuarioDAO usuarioDAO;

	public CadastraUsuarioController() {
		usuarioDAO = DAOFactory.get().usuarioDAO();
	}

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela(event, "Login.fxml");
	}

	@FXML
	void FinalizaCadastro(ActionEvent event) {
		Usuario usuario = novoUsuario();
		usuarioDAO.salvar(usuario);
		AbreTela(event, "Login.fxml");
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

	public void AbreTela(ActionEvent event, String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + tela));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			AnchorPane produtoView = (AnchorPane) loader.load();
			Scene scene = new Scene(produtoView);
			scene.getStylesheets().add("/css/style.css");
			stage.setScene(scene);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
