package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
    	AbreTela("RecuperaSenha.fxml");
    }

    @FXML
    void RedefinirSenha(ActionEvent event) {

    }
    
    public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(Main.PATH_VIEW + tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelPrincipal.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
