package controller;

import java.io.IOException;
import dao.CidadeDAO;
import dao.EstadoDAO;
import dao.PaisDAO;
import dao.RestauranteDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.Cidade;
import model.Estado;
import model.Pais;
import model.Restaurante;

public class CadastraRestauranteController {

	@FXML
	private ComboBox<Pais> cbPais;

	@FXML
	private ComboBox<Cidade> cbCidade;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private Button btnAvaliacao;

	@FXML
	private TextField tfCep;

	@FXML
	private DatePicker dpVisita;

	@FXML
	private TextField tfNumero;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private TextField tfTelefone;

	@FXML
	private Button btnVoltar;

	@FXML
	private ComboBox<Restaurante> cbTema;

	@FXML
	private TextField tfValor;

	@FXML
	private ComboBox<Restaurante> cbTipoEstabelecimento;

	@FXML
	private TextField tfRua;

	@FXML
	private TextField tfNome;
	/*****************************************************************************************************************************************/

	private PaisDAO paisDAO;
	private EstadoDAO estadoDAO;
	private CidadeDAO cidadeDAO;
	private RestauranteDAO restauranteDAO;

	/*****************************************************************************************************************************************/

	public CadastraRestauranteController() {
		restauranteDAO = DAOFactory.get().restauranteDAO();
		cidadeDAO = DAOFactory.get().cidadeDAO();
		estadoDAO = DAOFactory.get().estadoDAO();
		paisDAO = DAOFactory.get().paisDAO();
	}

	/*****************************************************************************************************************************************/

	@FXML
	public void initialize() {
		montaComboPais();
		montaComboEstado();
		montaComboCidade();
	}

	/*****************************************************************************************************************************************/

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("TelaVazia.fxml");
	}

	/*****************************************************************************************************************************************/
	@FXML
	void Avaliacao(ActionEvent event) {
		Restaurante restaurante = criaRestaurante();
		restauranteDAO.salvar(restaurante);

		AbreTela("Avaliacao.fxml");
	}
	/*_____________________________________________________________________________________________________________________________________*/

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
	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	// Metodos nao usados no fx
	// Metodos utilizados para montar os combobox
	// private void montaComboPais() {
	// cbPais.getItems().addAll(paisDAO.todos());
	// cbPais.setCellFactory((comboBox) -> {
	// return new ListCellBean<Pais>();
	// });
	// cbPais.setConverter(new StringConverterBean<>());
	// }

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	private void montaComboPais() {
		cbPais.getItems().addAll(paisDAO.todos());

		cbPais.setCellFactory((comboBox) -> {
			return new ListCell<Pais>() {
				@Override
				protected void updateItem(Pais item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbPais.setConverter(new StringConverter<Pais>() {
			@Override
			public String toString(Pais pais) {
				if (pais == null) {
					return null;
				} else {
					return pais.getCodigo() + " - " + pais.getNome();
				}
			}

			@Override
			public Pais fromString(String personString) {

				return null;
			}
		});
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	private void montaComboEstado() {
		cbEstado.getItems().addAll(estadoDAO.todos());

		cbEstado.setCellFactory((comboBox) -> {
			return new ListCell<Estado>() {
				@Override
				protected void updateItem(Estado item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbEstado.setConverter(new StringConverter<Estado>() {
			@Override
			public String toString(Estado estado) {
				if (estado == null) {
					return null;
				} else {
					return estado.getCodigo() + " - " + estado.getNome();
				}
			}

			@Override
			public Estado fromString(String personString) {

				return null;
			}
		});
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	private void montaComboCidade() {
		cbCidade.getItems().addAll(cidadeDAO.todos());

		cbCidade.setCellFactory((comboBox) -> {
			return new ListCell<Cidade>() {
				@Override
				protected void updateItem(Cidade item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbCidade.setConverter(new StringConverter<Cidade>() {
			@Override
			public String toString(Cidade cidade) {
				if (cidade == null) {
					return null;
				} else {
					return cidade.getCodigo() + " - " + cidade.getNome();
				}
			}

			@Override
			public Cidade fromString(String personString) {

				return null;
			}
		});
	}
	/*_____________________________________________________________________________________________________________________________________*/

	private Restaurante criaRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(tfNome.getText());
		restaurante.setRua(tfRua.getText());
		restaurante.setNumero(tfNumero.getText());
		restaurante.setPais(cbPais.getValue());
		restaurante.setEstado(cbEstado.getValue());
		restaurante.setCidade(cbCidade.getValue());
		restaurante.setTema(cbTema.getValue().toString());
		restaurante.setTipo(cbTipoEstabelecimento.getValue().toString());
		restaurante.setData(dpVisita.getValue().toString());
		restaurante.setTelefone(tfTelefone.getText());
		restaurante.setValorGasto(Double.valueOf(tfValor.getText()));
		return restaurante;
	}

}
