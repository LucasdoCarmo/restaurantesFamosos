package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Cidade;
import model.Estado;
import model.Pais;
import model.Restaurante;
import model.UF;

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
	private TextField tfNome;

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
	private TableView<?> tbResultado;

	@FXML
	private Button btnBuscar;

	private RestauranteDAO restauranteDAO;

	@FXML
	void MaiorPreco(ActionEvent event) {
		ObservableList<Restaurante> restaurantes = restauranteDAO.todosTabela();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	@FXML
	void MenorPreco(ActionEvent event) {

	}

	@FXML
	void Buscar(ActionEvent event) {
		if (!tfNome.getText().isEmpty()) {
			if (tfNome.getText().length() > 3) {
				List<Restaurante> restaurantes = restauranteDAO.getPorNome(tfNome.getText());
				tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
			}
			if (tfNome.getText().isEmpty()) {
				atualizaTabela();
			}
		} else if (!cbTipoEstabelecimento.getSelectionModel().isEmpty()) {
			List<Restaurante> restaurantes = restauranteDAO.getPorTipo(cbTipoEstabelecimento.getValue());
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
			atualizaTabela();
		} else if (!cbCidade.getSelectionModel().isEmpty()) {
			if (!cbPais.getSelectionModel().isEmpty()) {
				if (!cbEstado.getSelectionModel().isEmpty()) {
					List<Restaurante> restaurantes = restauranteDAO.getPorCidade(cbCidade.getValue());
					tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
					atualizaTabela();
				}
			}
		}

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
		AbreTela("EditaRestaurante.fxml");
	}

	private void atualizaTabela() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
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
