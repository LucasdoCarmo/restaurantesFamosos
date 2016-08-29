package controller;

import java.io.IOException;

import application.Main;
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

    @FXML
    void CadastrarNovo(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + "CadastraRestaurante.fxml"));
		try {
			AnchorPane cadastroRestauranteView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(cadastroRestauranteView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

    }

    @FXML
    void EditarPerfil(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + "EditaPerfil.fxml"));
		try {
			AnchorPane editaPerfilView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(editaPerfilView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void GerarRelatorio(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + "Relatorio.fxml"));
		try {
			AnchorPane relatorioView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(relatorioView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void Sair(ActionEvent event) {

    }

}

