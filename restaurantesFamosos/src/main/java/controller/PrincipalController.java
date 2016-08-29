package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalController {

	@FXML
	private BorderPane panelPrincipal;

	@FXML
	private Menu menuBuscar;

	@FXML
	private Button btnBuscar;

	@FXML
	void MenuBuscar(ActionEvent event) {

	}

	@FXML
	void Buscar(ActionEvent event) {
		
		// exemplo de como abrir uma tela dentro de outra
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + "RedefiniSenha.fxml"));
		try {
			AnchorPane buscarView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(buscarView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
