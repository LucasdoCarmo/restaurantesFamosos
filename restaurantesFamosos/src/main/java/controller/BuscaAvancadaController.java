package controller;

import java.io.IOException;
import java.util.Collection;

import dao.RestauranteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Cidade;
import model.Estado;
import model.Pais;
import model.Restaurante;

public class BuscaAvancadaController {

	@FXML
	private ComboBox<Pais> cbPais;

	@FXML
	private ComboBox<Cidade> cbCidade;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private Button btnAvaliar;

	@FXML
	private TextField tfEndereco;

	@FXML
	private Button btnPrecoMaior;

	@FXML
	private Button btnPrecoMenor;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnVoltar;

	@FXML
	private ComboBox<Restaurante> cbTipoEstabelecimento;

	@FXML
	private Button btnExcluir;

	@FXML
	private TableView<?> tbResultado;

	@FXML
	private Button btnBuscar;
	
	private RestauranteDAO restauranteDAO;

	@FXML
	void MaiorPreco(ActionEvent event) {

	}

	@FXML
	void MenorPreco(ActionEvent event) {

	}

	@FXML
	void Buscar(ActionEvent event) {

	}

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Buscar.fxml");
	}

	@FXML
	void Avaliar(ActionEvent event) {
		AbreTela("Avaliacao.fxml");
	}

	@FXML
	void Editar(ActionEvent event) {

	}

	@FXML
	void Excluir(ActionEvent event) {
		//restauranteDAO.excluir(Long.valueOf(tfCodigo.getText()));
		atualizaTabela();
	}

	
	private void atualizaTabela() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		//tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
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
