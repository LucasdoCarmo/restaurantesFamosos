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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Cidade;
import model.Estado;
import model.Restaurante;

public class BuscaAvancadaController {


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
	private TextField tfTipoEstabelecimento;

	@FXML
	private TableView<Restaurante> tbResultado;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableColumn<Restaurante, String> tcNome;

	@FXML
	private TableColumn<Restaurante, String> tcTelefone;

	@FXML
	private TableColumn<Restaurante, String> tcTipo;

	@FXML
	private TableColumn<Restaurante, String> tcRua;

	@FXML
	private TableColumn<Restaurante, String> tcNumero;

	@FXML
	private TableColumn<Restaurante, String> tcTema;

	@FXML
	private TableColumn<Restaurante, String> tcCidade;

	private RestauranteDAO restauranteDAO;

	private ObservableList<Restaurante> restauranteList = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		// Monta a tabela
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("nomeUF"));
		tcRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tcTema.setCellValueFactory(new PropertyValueFactory<>("tema"));
		tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tbResultado.setItems(restauranteList);
	}

	@FXML
	void MaiorPreco(ActionEvent event) {
		// implementar lógica para filtrar por menor e por maior preco
		ObservableList<Restaurante> restaurantes = restauranteDAO.todosTabela();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	@FXML
	void MenorPreco(ActionEvent event) {
		ObservableList<Restaurante> restaurantes = restauranteDAO.todosTabela();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	@FXML
	void Buscar(ActionEvent event) {
		// Filtra por qual busca o usuário vai escolher
		if (!tfNome.getText().isEmpty()) {
			if (tfNome.getText().length() > 3) {
				List<Restaurante> restaurantes = restauranteDAO.getPorNome(tfNome.getText());
				tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
			}
			if (tfNome.getText().isEmpty()) {
				atualizaTabela();
			}
		} else if (!tfTipoEstabelecimento.getText().isEmpty()) {
			List<Restaurante> restaurantes = restauranteDAO.getPorTipo(tfTipoEstabelecimento.getText());
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
			atualizaTabela();
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

	// metodo que abre próxima tela
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
