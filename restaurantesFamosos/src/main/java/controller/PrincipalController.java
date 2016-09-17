package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalController {

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private Label lblNomeUsuario;

	@FXML
	private Button btnRelatorio;

	@FXML
	private MenuItem menuNovo;

	@FXML
	private Button btnSair;

	@FXML
	private MenuItem menuEditar;

	@FXML
	private Button btnBuscar;

	@FXML
	void Buscar(ActionEvent event) {
		// método usado para dispensar o exesso de código para abrir telas
		AbreTela("Buscar.fxml");

	}

	@FXML
	void CadastrarNovo(ActionEvent event) {
		AbreTela("CadastraRestaurante.fxml");

	}

	@FXML
	void EditarPerfil(ActionEvent event) {
		AbreTela("EditaPerfil.fxml");

	}

	@FXML
	void GerarRelatorio(ActionEvent event) {
		AbreTela("Relatorio.fxml");
	}

	@FXML
	void Sair(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/Login.fxml"));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelPrincipal.setTop(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
