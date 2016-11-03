package controller;

import java.io.IOException;
import application.Main;
import dao.CidadeDAO;
import dao.EstadoDAO;
//import dao.PaisDAO;
import dao.RestauranteDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import model.Cidade;
import model.Estado;
//import model.Pais;
import model.Restaurante;

public class CadastraRestauranteController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private ComboBox<Cidade> cbCidade;

	@FXML
	private ComboBox<Estado> cbEstado;

	// @FXML
	// private ComboBox<Pais> cbPais;

	@FXML
	private Button btnAvaliacao;

	@FXML
	private Button btnVoltar;

	@FXML
	private DatePicker dpVisita;

	@FXML
	private TextField tfCep;

	@FXML
	private TextField tfNumero;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfTema;

	@FXML
	private TextField tfValor;

	@FXML
	private TextField tfTipoEstabelecimento;

	@FXML
	private TextField tfRua;

	@FXML
	private TextField tfNome;
	/*****************************************************************************************************************************************/
	// private PaisDAO paisDAO;
	private EstadoDAO estadoDAO;
	private CidadeDAO cidadeDAO;
	private RestauranteDAO restauranteDAO;

	/*****************************************************************************************************************************************/

	public CadastraRestauranteController() {
		restauranteDAO = DAOFactory.get().restauranteDAO();
		// paisDAO = DAOFactory.get().paisDAO();
		cidadeDAO = DAOFactory.get().cidadeDAO();
		estadoDAO = DAOFactory.get().estadoDAO();
	}

	/*****************************************************************************************************************************************/

	@FXML
	public void initialize() {
		// montaComboPais();
		montaComboEstado();
		montaComboCidade();
	}

	// Busca no banco as cidades conforme o estado selecionado
	@FXML
	void EstadoSelecionado(ActionEvent event) {
		cbCidade.getItems().removeAll(cbCidade.getItems());
		cbCidade.getItems().clear();

		cbCidade.getItems().addAll(cidadeDAO.porEstado(cbEstado.getValue().getCodigo()));
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

	/*****************************************************************************************************************************************/

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Principal.fxml");
	}

	/*****************************************************************************************************************************************/
	@FXML
	void Avaliacao(ActionEvent event) {
		Restaurante restaurante = criaRestaurante();
		restauranteDAO.salvar(restaurante);

		// try {
		AbreTela("Avaliacao.fxml");
		// } catch (Exception e) {
		// Alert alert = new Alert(AlertType.ERROR, "Erro interno código:
		// #6B8E23");
		// alert.show();
		// }
	}
	/* _____________________________________________________________________________________________________________________________________ */

	public void AbreTela(String tela) {
		stack.getChildren().clear();
		try {
			Node no = FXMLLoader.load(getClass().getResource(Main.PATH_VIEW + tela));
			stack.getChildren().add(no);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// public Node getNode(String node) {
	// Node no = null;
	// try {
	// no = FXMLLoader.load(getClass().getResource(Main.PATH_VIEW + node));
	// } catch (Exception e) {
	// }
	// return no;
	// }

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	// Metodos nao usados no fx
	// Metodos utilizados para montar os combobox

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	// Caso exista a necessidade de se implementar um combo de pais no sistema
	// este método está pronto para renderizar o combo com o nome dos paises a
	// serem escolhidos
	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	// private void montaComboPais() {
	// cbPais.getItems().addAll(paisDAO.todos());

	// cbPais.setCellFactory((comboBox) -> {
	// return new ListCell<Pais>() {
	// @Override
	// protected void updateItem(Pais item, boolean empty) {
	// super.updateItem(item, empty);
	// if (item == null || empty) {
	// setText(null);
	// } else {
	// setText(item.getNome());
	// }
	// }
	// };
	// });
	// cbPais.setConverter(new StringConverter<Pais>() {
	// @Override
	// public String toString(Pais pais) {
	// if (pais == null) {
	// return null;
	// } else {
	// return pais.getCodigo() + " - " + pais.getNome();
	// }
	// }
	//
	// @Override
	// public Pais fromString(String personString) {
	//
	// return null;
	// }
	// });
	// }

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

	/* _____________________________________________________________________________________________________________________________________ */

	private Restaurante criaRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(tfNome.getText());
		restaurante.setRua(tfRua.getText());
		restaurante.setNumero(tfNumero.getText());
		restaurante.setCidade(cbCidade.getValue());
		restaurante.setTema(tfTema.getText());
		restaurante.setTipo(tfTipoEstabelecimento.getText());
		restaurante.setTelefone(tfTelefone.getText());
		return restaurante;
	}

}
