package controller;

import java.io.IOException;
import java.util.Collection;
import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
		if(!tfNome.getText().isEmpty()){
			BuscarPorNome(tfNome.getText());
		}else if(!tfTipoEstabelecimento.getText().isEmpty()){
			BuscarPorTipo(tfTipoEstabelecimento.getText());
		}else if (!cbEstado.getSelectionModel().isEmpty()){
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

	private void atualizaTabela() {
		Collection<Restaurante> restaurantes = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
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
			atualizaTabela();
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
			atualizaTabela();
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
