package controller;

import java.util.Collection;
import application.Main;
import dao.RestauranteDAO;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Restaurante;

public class BuscarController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private Button btnAvaliar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnBuscaAvancada;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfTema;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfTipo;

	@FXML
	private TableView<Restaurante> tbResultado;

	@FXML
	private TableColumn<Restaurante, String> tcNome;

	@FXML
	private TableColumn<Restaurante, String> tcTelefone;

	// @FXML
	// private TableColumn<Restaurante, String> tcTipo;

	@FXML
	private TableColumn<Restaurante, String> tcTema;

	@FXML
	private TableColumn<Restaurante, String> tcRua;

	@FXML
	private TableColumn<Restaurante, String> tcNumero;

	// @FXML
	// private TableColumn<Cidade, String> tcCidade;

	private RestauranteDAO restauranteDAO;

	/* _____________________________________________________________________________________________________________________________________ */

	public BuscarController() {
		restauranteDAO = DAOFactory.get().restauranteDAO();
	}

	@FXML
	public void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		// tcTipo.setCellValueFactory(new
		// PropertyValueFactory<>("Tipo_de_estabelicimento"));
		tcRua.setCellValueFactory(new PropertyValueFactory<>("Rua"));
		tcNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
		tcTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));
		// tcCidade.setCellValueFactory(new PropertyValueFactory<>("idCidade"));
	}

	@FXML
	void Buscar(ActionEvent event) {
		if (!tfNome.getText().isEmpty()) {
			BuscarPorNome(tfNome.getText());
		} else if (!tfTema.getText().isEmpty()) {
			BuscarPorTema(tfTema.getText());
		} else if (!tfTipo.getText().isEmpty()) {
			BuscarPorTipo(tfTipo.getText());
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção!! Os dados não foram encontrados", ButtonType.CLOSE);
			alert.show();
			atualizaTabelaTodos();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		// restauranteDAO.excluir(tbResultado.getSelectionModel().getSelectedIndex());
		atualizaTabelaTodos();
	}

	@FXML
	void Editar(ActionEvent event) {
		AbreTela("EditaRestaurante.fxml");
	}

	@FXML
	void Avaliar(ActionEvent event) {
		AbreTela("Avaliacao.fxml");
	}

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Principal.fxml");
	}

	@FXML
	void BuscaAvancada(ActionEvent event) {
		AbreTela("BuscaAvancada.fxml");
	}

	/* _____________________________________________________________________________________________________________________________________ */

	private void atualizaTabelaTodos() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	/* _____________________________________________________________________________________________________________________________________ */

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

	public void BuscarPorTema(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorTema(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Tema não encontrado! carregando todos os restaurantes.",
					ButtonType.CLOSE);
			alert.show();
			atualizaTabelaTodos();
		}
	}

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

}