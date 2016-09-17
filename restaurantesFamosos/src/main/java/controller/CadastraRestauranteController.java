package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CadastraRestauranteController {

    @FXML
    private ComboBox<?> cbPais;

    @FXML
    private ComboBox<?> cbCidade;

    @FXML
    private ComboBox<?> cbEstado;

    @FXML
    private Button btnAvaliacao;

    @FXML
    private TextField tfCep;

    @FXML
    private DatePicker dpVisita;

    @FXML
    private TextField tfNumero;

    @FXML
    private BorderPane panelSecundario;

    @FXML
    private TextField tfTelefone;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<?> cbTema;

    @FXML
    private TextField tfValor;

    @FXML
    private ComboBox<?> cbTipoEstabelecimento;

    @FXML
    private TextField tfRua;

    @FXML
    private TextField tfNome;

    @FXML
    void Voltar(ActionEvent event) {

    }

    @FXML
    void Avaliacao(ActionEvent event) {
    	AbreTela("Avaliacao.fxml");
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
