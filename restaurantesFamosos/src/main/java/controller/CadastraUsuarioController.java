package controller;

import java.io.IOException;

import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Usuario;

public class CadastraUsuarioController {

    @FXML
    private BorderPane panelSecundario;
    
    @FXML
    private TextField tfSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfConfirmaSenha;

    @FXML
    private Button btnFinaliza;

    @FXML
    private TextField tfConfirmaEmail;

    @FXML
    private TextField tfNome;
    
    private UsuarioDAO usuarioDAO;

    @FXML
    void Voltar(ActionEvent event) {
    	AbreTela("Login.fxml");
    }

    @FXML
    void FinalizaCadastro(ActionEvent event) {
    	novoUsuario();
    	AbreTela("Login.fxml");
    }
    
    
	private Usuario novoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(tfNome.getText());
		
		String email =  tfEmail.getText();
		String confirmaEmail = tfConfirmaEmail.getText();
		if (email != confirmaEmail) {
			usuario.setEmail(tfEmail.getText());
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção!! Verifique se o email está correto", ButtonType.CLOSE);
			alert.show();
		}
		String senha = tfConfirmaSenha.getText();
		String confirmaSenha = tfSenha.getText();
		if(senha != confirmaSenha){
			usuario.setSenha(tfSenha.getText());	
		}else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção! As senhas não são iguais", ButtonType.CLOSE);
			alert.show();
		}
		usuarioDAO.salvar(usuario);
		return usuario;
	}
	
	 public void AbreTela(String tela) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/telas/" +tela));
			try {
				AnchorPane telaView = (AnchorPane) loader.load();
				panelSecundario.setCenter(telaView);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}  

}
