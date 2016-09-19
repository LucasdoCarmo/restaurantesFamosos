package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class BuscarController {

	@FXML
	private BorderPane panelSecundario;
	
    @FXML
    private Button btnAvaliar;

    @FXML
    private Button btnTema;

    @FXML
    private Button btnEditar;

    @FXML
    private ComboBox<?> cbTipo;

    @FXML
    private Button btnBusca;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<?> cbTema;

    @FXML
    private Button btnBuscaAvancada;

    @FXML
    private Button btnTipo;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableView<?> tbResultado;

    @FXML
    private Button btnNome;

    @FXML
    private TextField tfNome;

    @FXML
    void Buscar(ActionEvent event) {

    }

    @FXML
    void Excluir(ActionEvent event) {

    }

    @FXML
    void Editar(ActionEvent event) {

    }

    @FXML
    void Avaliar(ActionEvent event) {

    }

    @FXML
    void Voltar(ActionEvent event) {

    }

    @FXML
    void BuscarNome(ActionEvent event) {

    }

    @FXML
    void BuscaTema(ActionEvent event) {

    }

    @FXML
    void BuscaTipoEstabelecimento(ActionEvent event) {

    }

    @FXML
    void BuscaAvancada(ActionEvent event) {
    	AbreTela("BuscaAvancada.fxml");
    }
    
    public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" +tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelSecundario.setTop(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}    
}
