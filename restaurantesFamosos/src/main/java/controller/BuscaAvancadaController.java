package controller;

import java.util.Collection;
import application.Main;
import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Cidade;
import model.Estado;
import model.Restaurante;

public class BuscaAvancadaController {

	@FXML
	private StackPane stack;

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

	// @FXML
	// private TableColumn<Restaurante, String> tcTipo;

	@FXML
	private TableColumn<Restaurante, String> tcRua;

	@FXML
	private TableColumn<Restaurante, String> tcNumero;

	@FXML
	private TableColumn<Restaurante, String> tcTema;

	// @FXML
	// private TableColumn<Restaurante, String> tcCidade;

	private RestauranteDAO restauranteDAO;

	private ObservableList<Restaurante> restauranteList = FXCollections.observableArrayList();

	
	//IMPLEMENTAR METODOS DE MONTAR OS COMBO
	@FXML
	private void initialize() {
		// Monta a tabela
		tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("nomeUF"));
		tcRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tcTema.setCellValueFactory(new PropertyValueFactory<>("tema"));
		// tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tbResultado.setItems(restauranteList);
	}

	@FXML
	void MaiorPreco(ActionEvent event) {
		// implementar lógica para filtrar por menor e por maior preco
		Collection<Restaurante> restaurantes = restauranteDAO.getMaiorPreco();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	@FXML
	void MenorPreco(ActionEvent event) {
		Collection<Restaurante> restaurantes = restauranteDAO.getMenorPreco();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	@FXML
	void Buscar(ActionEvent event) {
		// Filtra por qual busca o usuário vai escolher
		if (!tfNome.getText().isEmpty()) {
			BuscarPorNome(tfNome.getText());
		} else if (!tfTipoEstabelecimento.getText().isEmpty()) {
			BuscarPorTipo(tfTipoEstabelecimento.getText());
		} else if (!cbEstado.getSelectionModel().isEmpty()) {
			BuscarPorCidade(cbCidade.getValue().toString());
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

	/* _____________________________________________________________________________________________________________________________________ */

	public void BuscarPorNome(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorNome(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION,
					"Restaurante não encontrado! carregando todos os restaurantes.", ButtonType.CLOSE);
			alert.show();
			atualizaTabelaTodos();
		}
	}

	/* _____________________________________________________________________________________________________________________________________ */

	public void BuscarPorTipo(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorTipo(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Tipo não encontrado! carregando todos os restaurantes.",
					ButtonType.CLOSE);
			alert.show();
			atualizaTabelaTodos();
		}
	}

	/* _____________________________________________________________________________________________________________________________________ */

	public void BuscarPorCidade(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorCidade(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
	}

	/* _____________________________________________________________________________________________________________________________________ */

	// metodo que abre próxima tela
	public void AbreTela(String tela) {
		stack.getChildren().clear();
		stack.getChildren().add(getNode(tela));
	}

	public Node getNode(String node) {
		Node no = null;
		try {
			no = FXMLLoader.load(getClass().getResource(Main.PATH_VIEW + node));
		} catch (Exception e) {
		}
		return no;
	}

	private void atualizaTabelaTodos() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

}
