package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RedefiniSenhaController {

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private TextField tfNovaSenha;

	@FXML
	private Button btnRedefinirSenha;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfConfirmaSenha;

	@FXML
	void NovaSenha(ActionEvent event) {

	}

	@FXML
	void ConfirmaSenha(ActionEvent event) {

	}

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela(event,"RecuperaSenha.fxml");
	}

	@FXML
	void RedefinirSenha(ActionEvent event) {

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
