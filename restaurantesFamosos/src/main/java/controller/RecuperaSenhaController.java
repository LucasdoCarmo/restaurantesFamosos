package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    	AbreTela("RedefiniSenha.fxml");
    }

    @FXML
    void Cancelar(ActionEvent event) {
    	AbreTela("Login.fxml");
    }
    
    public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" +tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
