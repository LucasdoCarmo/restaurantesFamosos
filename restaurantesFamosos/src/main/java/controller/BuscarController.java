package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import br.edu.unoesc.jdbcOO.model.UF;
import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Restaurante;

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
    private TableView<Restaurante> tbResultado;

    @FXML
    private Button btnNome;

    RestauranteDAO restauranteDAO;
    
    @FXML
    private TextField tfNome;

    @FXML
    void Buscar(ActionEvent event) {
    	String pesquisa = tfNome.getText() + event.getTarget();
		if (pesquisa.length() > 2) {
			List<Restaurante> restaurantes = restauranteDAO.getPorNome(pesquisa);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
		if (pesquisa.length() < 2) {
			atualizaTabela();
		}
    	
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
    	String pesquisa = tfNome.getText() + event.getTarget();
		if (pesquisa.length() > 2) {
			List<Restaurante> restaurantes = restauranteDAO.getPorNome(pesquisa);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
		if (pesquisa.length() < 2) {
			atualizaTabela();
		}
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
    private void atualizaTabela() {
		Collection<Restaurante> ufs = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(ufs));
	}
}
