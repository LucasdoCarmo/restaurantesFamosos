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

public class RecuperaSenhaController {

	@FXML
	private BorderPane panelPrincipal;
	
    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnEnviar;

    @FXML
    private TextField tfCodigo;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField tfEmail;

    @FXML
    void Enviar(ActionEvent event) {

    }

    @FXML
    void Confirmar(ActionEvent event) {
    	AbreTela(event,"RedefiniSenha.fxml");
    }

    @FXML
    void Cancelar(ActionEvent event) {
    	AbreTela(event,"Login.fxml");
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
