package controller;

import java.io.IOException;
import java.util.Collection;
import dao.RestauranteDAO;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Restaurante;

public class BuscarController {

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

	@FXML
	private TableColumn<Restaurante, String> tcTipo;

	@FXML
	private TableColumn<Restaurante, String> tcTema;

	private RestauranteDAO restauranteDAO;

	/* _____________________________________________________________________________________________________________________________________ */

	public BuscarController() {
		restauranteDAO = DAOFactory.get().restauranteDAO();
	}

	@FXML
	public void initialize() {
		tcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		tcTema.setCellValueFactory(new PropertyValueFactory<>("Tema"));
		tcTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo_de_estabelecimento"));
	}

	@FXML
	void Buscar(ActionEvent event) {
		if (!tfNome.getText().isEmpty()) {
			BuscarPorNome(tfNome.getText());
			atualizaTabela();
		} else if (!tfTema.getText().isEmpty()) {
			BuscarPorTema(tfTema.getText());
			atualizaTabela();
		} else if (!tfTipo.getText().isEmpty()) {
			BuscarPorTipo(tfTipo.getText());
			atualizaTabela();
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção!! Os dados não foram encontrados", ButtonType.CLOSE);
			alert.show();
		}
	}

	@FXML
	void Excluir(ActionEvent event) {
		// restauranteDAO.excluir(tbResultado.getSelectionModel().getSelectedIndex());
		atualizaTabela();
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
		AbreTela("TelaVazia.fxml");
	}

	@FXML
	void BuscaAvancada(ActionEvent event) {
		AbreTela("BuscaAvancada.fxml");
	}
	
	/* _____________________________________________________________________________________________________________________________________ */

	private void atualizaTabela() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
	}

	/* _____________________________________________________________________________________________________________________________________ */

	public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" + tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelSecundario.setTop(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/* _____________________________________________________________________________________________________________________________________ */
	
	public void BuscarPorNome(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorNome(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			atualizaTabela();
		}
	}

	public void BuscarPorTema(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorTema(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			atualizaTabela();
		}
	}

	public void BuscarPorTipo(String texto) {
		if (texto != null) {
			Collection<Restaurante> restaurantes = restauranteDAO.getPorTipo(texto);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		} else {
			atualizaTabela();
		}
	}
	
	/* _____________________________________________________________________________________________________________________________________ */

	
}