package controller;

import java.io.IOException;
import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Usuario;

public class EditaPerfilController {

	@FXML
    private BorderPane panelSecundario;
	
    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField tfCodigo;
    
    @FXML
    private TextField tfSenha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfConfirmaSenha;

    @FXML
    private TextField tfNome;

    private UsuarioDAO usuarioDAO;
    
    @FXML
    void Voltar(ActionEvent event) {
    	AbreTela("TelaVazia.fxml");
    }

    @FXML
    void Confirmar(ActionEvent event) {
    	Usuario usuario = modificaUsuario();
    	usuarioDAO.salvar(usuario);
    }
    
    
	private Usuario modificaUsuario() {
    	//implementar uma forma de salvar a avaliação do usuário
		usuarioDAO.excluir(Long.valueOf(tfCodigo.getText()));
		Usuario usuario = new Usuario();
		usuario.setNome(tfNome.getText());
		usuario.setEmail(tfEmail.getText());
		if(tfConfirmaSenha.getText() == tfSenha.getText()){
			if(!tfSenha.getText().isEmpty())
			usuario.setSenha(tfSenha.getText());	
		}
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
