package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ConfirmaCadastroController {

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfCodigo;

	@FXML
	private Button btnEntrar;

	@FXML
	void Entrar(ActionEvent event) {

	}

	@FXML
	void Voltar(ActionEvent event) {

	}

	public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" + tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelSecundario.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
