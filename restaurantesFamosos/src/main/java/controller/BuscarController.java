package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	private TextField tfTipo;

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField tfTema;

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

	private RestauranteDAO restauranteDAO;

	@FXML
	private TextField tfNome;

	@FXML
	void Buscar(ActionEvent event) {
		// String pesquisa = tfNome.getText() + event.getTarget();

		tfNome.getText();
		if (!tfNome.getText().isEmpty()) {
			BuscarPorNome(event);
		} else if (!tfTema.getText().isEmpty()) {
			BuscarPorTema(event);
		} else if (!tfTipo.getText().isEmpty()) {
			BuscarPorTipo(event);
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Atenção!! Os dados n�o foram encontrados", ButtonType.CLOSE);
			alert.show();
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
		BuscarPorNome(event);
	}

	@FXML
	void BuscarTema(ActionEvent event) {
		BuscarPorTema(event);
	}

	@FXML
	void BuscarTipo(ActionEvent event) {
		BuscarPorTipo(event);
	}

	@FXML
	void BuscaAvancada(ActionEvent event) {
		AbreTela("BuscaAvancada.fxml");
	}

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

	private void atualizaTabela() {
		Collection<Restaurante> ufs = restauranteDAO.todos();
		tbResultado.setItems(FXCollections.observableArrayList(ufs));
	}

	public void BuscarPorNome(ActionEvent event) {
		String pesquisa = tfNome.getText() + event.getTarget();
		if (pesquisa.length() > 0) {
			List<Restaurante> restaurantes = restauranteDAO.getPorNome(pesquisa);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
		if (pesquisa.length() < 0) {
			atualizaTabela();
		}
	}

	public void BuscarPorTema(ActionEvent event) {
		String pesquisa = tfTema.getText() + event.getTarget();
		if (pesquisa.length() > 0) {
			List<Restaurante> restaurantes = restauranteDAO.getPorTema(pesquisa);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
		if (pesquisa.length() < 0) {
			atualizaTabela();
		}
	}

	public void BuscarPorTipo(ActionEvent event) {
		String pesquisa = tfTipo.getText() + event.getTarget();
		if (pesquisa.length() > 0) {
			List<Restaurante> restaurantes = restauranteDAO.getPorTipo(pesquisa);
			tbResultado.setItems(FXCollections.observableArrayList(restaurantes));
		}
		if (pesquisa.length() < 0) {
			atualizaTabela();
		}

	}
}