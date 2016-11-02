package controller;

import java.net.URL;
import application.Main;
import conexao.ConexaoMysqlProducao;
import dao.CidadeDAO;
import dao.EstadoDAO;
//import dao.PaisDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import model.Estado;
//import model.Pais;
import model.Restaurante;
import model.Cidade;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioController {

	// @FXML
	// private ComboBox<Pais> cbPais;

	@FXML
	private StackPane stack;

	@FXML
	private ComboBox<Estado> cbEstado;

	@FXML
	private ComboBox<Cidade> cbCidade;

	@FXML
	private ComboBox<Restaurante> cbTipo;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnGeraRelatorio;

	@FXML
	private Button btn5melhores;

	@FXML
	private Button btn10Melhores;

	@FXML
	private Button btn15Melhores;

	@FXML
	private Button btn20Melhores;

	@FXML
	private Button btnMaior;

	@FXML
	private Button btnMenor;

	@FXML
	private Button btnTodos;

	@FXML
	private TableView<Restaurante> tbResultado;

	// private PaisDAO paisDAO;
	private EstadoDAO estadoDAO;
	private CidadeDAO cidadeDAO;

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	public RelatorioController() {
		// paisDAO = DAOFactory.get().paisDAO();
		cidadeDAO = DAOFactory.get().cidadeDAO();
		estadoDAO = DAOFactory.get().estadoDAO();
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void Todos(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioTodos.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioTodos.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void VinteMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio20Melhores.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio20.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void CincoMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio5Melhores.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio5.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void DezMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio10Melhores.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio10.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void QuinzeMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio15Melhores.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio15.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void MaiorPreco(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioMaiorPreco.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioPrecoMaior.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void MenorPreco(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioMenorPreco.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioPrecoMenor.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	public void Cidade() {
		URL url = getClass().getResource("/relatorio/RelatorioEndereco.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioEndereco.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	public void Tipo() {
		URL url = getClass().getResource("/relatorio/RelatorioTipo.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null,
					new ConexaoMysqlProducao().get());
			JasperViewer.viewReport(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorioTipo.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void GerarRelatorio(ActionEvent event) {
		if (!cbTipo.getSelectionModel().isEmpty()) {
			Tipo();
		} else if (!cbCidade.getSelectionModel().isEmpty()) {
			Cidade();
		}
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	void Sair(ActionEvent event) {
		AbreTela("Principal.fxml");
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	@FXML
	public void initialize() {
		// montaComboPais();
		montaComboEstado();
		montaComboCidade();
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

	// private void montaComboPais() {
	// cbPais.getItems().addAll(paisDAO.todos());
	//
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

	/*-------------------------------------------------------------------------------------------------------------------------------------*/

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
}
