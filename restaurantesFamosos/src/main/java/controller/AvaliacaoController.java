package controller;

import application.Main;
import conexao.Conexao;
import dao.AvaliacaoDAO;
import dao.RestauranteJDBC;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Avaliacao;

public class AvaliacaoController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private TextField tfNomeRestaurante;

	@FXML
	private RadioButton rbDentroDoEsperado;

	@FXML
	private ToggleGroup TempoEntrega;

	@FXML
	private RadioButton rbSatisfatorio;

	@FXML
	private RadioButton rbOtimo;

	@FXML
	private RadioButton rbMuitoDemorado;

	@FXML
	private RadioButton rbNota2;

	@FXML
	private ToggleGroup NotaAtendimento;

	@FXML
	private RadioButton rbNota3;

	@FXML
	private RadioButton rbNota4;

	@FXML
	private RadioButton rbNota5;

	@FXML
	private RadioButton rbNota1;

	@FXML
	private RadioButton rbRuimIrritante;

	@FXML
	private ToggleGroup QualidadeAtendimento;

	@FXML
	private RadioButton rbBomESimples;

	@FXML
	private RadioButton rbOtimoEducado;

	@FXML
	private RadioButton rbRegular;

	@FXML
	private RadioButton rbComEsperaComReserva;

	@FXML
	private ToggleGroup Espera;

	@FXML
	private RadioButton rbSemEsperaComReserva;

	@FXML
	private RadioButton rbSemEsperaSemReserva;

	@FXML
	private RadioButton rbLimpo;

	@FXML
	private RadioButton rbTematica;

	@FXML
	private ToggleGroup AparenciaInterna;

	@FXML
	private RadioButton rbNota1Aspectos;

	@FXML
	private ToggleGroup NotaAspectos;

	@FXML
	private RadioButton rbPremium;

	@FXML
	private ToggleGroup AparenciaExterna;

	@FXML
	private RadioButton rbNota3Aspectos;

	@FXML
	private RadioButton rbOrganizadoeRustico;

	@FXML
	private ToggleGroup Limpeza;

	@FXML
	private RadioButton rbLimpoBrilhando;

	@FXML
	private RadioButton rbBoa;

	@FXML
	private RadioButton rbDiscreta;

	@FXML
	private RadioButton rbNota4Aspectos;

	@FXML
	private RadioButton rbNota5Aspectos;

	@FXML
	private RadioButton rbNota2Aspectos;

	@FXML
	private RadioButton rbRuim;

	@FXML
	private RadioButton rbInadequado;

	@FXML
	private RadioButton rbSucos;

	@FXML
	private ToggleGroup BebidasSemAlcool;

	@FXML
	private RadioButton rbPoucas;

	@FXML
	private ToggleGroup BebidasAlcoolicas;

	@FXML
	private RadioButton rbNota1Cardapio;

	@FXML
	private ToggleGroup NotaCardapio;

	@FXML
	private RadioButton rbEspecifica;

	@FXML
	private ToggleGroup Variedade;

	@FXML
	private RadioButton rbNota3Cardapio;

	@FXML
	private RadioButton rbNenhuma;

	@FXML
	private RadioButton rbSucosRefrigerantesETC;

	@FXML
	private RadioButton rbVariedadesDeBebidas;

	@FXML
	private RadioButton rbGrandeVariedade;

	@FXML
	private RadioButton rbNota4Cardapio;

	@FXML
	private RadioButton rbAlgumasAprimoradas;

	@FXML
	private RadioButton rbNota5Cardapio;

	@FXML
	private RadioButton rbRefrigerantes;

	@FXML
	private RadioButton rbNota2Cardapio;

	@FXML
	private RadioButton rbNota4Pagamento;

	@FXML
	private ToggleGroup Pagamento;

	@FXML
	private ToggleGroup QualidadeValorPago;

	@FXML
	private RadioButton rbNota1Pagamento;

	@FXML
	private RadioButton rbNota2Pagamento;

	@FXML
	private RadioButton rbNota5Pagamento;

	@FXML
	private RadioButton rbCaro;

	@FXML
	private RadioButton rbCartaoDeDebito;

	@FXML
	private ToggleGroup OpcoesPagamento;

	@FXML
	private RadioButton rbDinheiro;

	@FXML
	private RadioButton rbCartaoDeCrediro;

	@FXML
	private RadioButton rbBom;

	@FXML
	private RadioButton rbNota3Pagamento;

	@FXML
	private RadioButton rbBarato;

	@FXML
	private TextArea taDescricao;

	@FXML
	private Button btnVoltar;

	@FXML
	private Button btnSalvar;

	private AvaliacaoDAO avaliacaoDAO;

	private Conexao conexao;

	@FXML
	void Voltar(ActionEvent event) {
		AbreTela("Principal.fxml");
	}

	public AvaliacaoController(Conexao conexao) {
		avaliacaoDAO = DAOFactory.get().avaliacaoDAO();
		avaliacaoDAO = DAOFactory.get().avaliacaoDAO();
		this.conexao = conexao;
	}

	@FXML
	void Salvar(ActionEvent event) {
		if (!tfNomeRestaurante.getText().isEmpty()) {
			RestauranteJDBC restaurante = new RestauranteJDBC(conexao);
			restaurante.getPorNome(tfNomeRestaurante.getText());
		}
		Avaliacao avaliacao = criaAvaliacao();
		avaliacao.setNotaAtendimento(Integer.valueOf(radioAtendimento()));
		avaliacao.setNotaAspecto(Integer.valueOf(radioAspecto()));
		avaliacao.setNotaComida(Integer.valueOf(radioCardapio()));
		avaliacao.setNotaPagamento(Integer.valueOf(radioPagamento()));
		// avaliacao.setNotaGeral(notaGeral());
		avaliacao.setAvaliacaoDescritiva(taDescricao.getText());
		avaliacaoDAO.salvar(avaliacao);

		AbreTela("CadastraVisita.fxml");
	}
	/* _____________________________________________________________________________________________________________________________________ */

	private Avaliacao criaAvaliacao() {
		String string = taDescricao.getText();
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliacaoDescritiva(string);
		return avaliacao;

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

	public String radioAtendimento() {
		String radio = null;
		if (rbNota1.isSelected()) {
			radio = rbNota1.getText();
		} else if (rbNota2.isSelected()) {
			radio = rbNota2.getText();
		} else if (rbNota3.isSelected()) {
			radio = rbNota3.getText();
		} else if (rbNota4.isSelected()) {
			radio = rbNota4.getText();
		} else if (rbNota5.isSelected()) {
			radio = rbNota5.getText();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Atenção! Você deve selecionar uma nota para o atendimento.",
					ButtonType.CLOSE);
			alert.show();
		}
		return radio;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	public String radioAspecto() {
		String radio = null;
		if (rbNota1Aspectos.isSelected()) {
			radio = rbNota1Aspectos.getText();
		} else if (rbNota2Aspectos.isSelected()) {
			radio = rbNota2Aspectos.getText();
		} else if (rbNota3Aspectos.isSelected()) {
			radio = rbNota3Aspectos.getText();
		} else if (rbNota4Aspectos.isSelected()) {
			radio = rbNota4Aspectos.getText();
		} else if (rbNota5Aspectos.isSelected()) {
			radio = rbNota5Aspectos.getText();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Atenção! Você deve selecionar uma nota para o aspecto.",
					ButtonType.CLOSE);
			alert.show();
		}
		return radio;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	public String radioCardapio() {
		String radio = null;
		if (rbNota1Cardapio.isSelected()) {
			radio = rbNota1Cardapio.getText();
		} else if (rbNota2Cardapio.isSelected()) {
			radio = rbNota2Cardapio.getText();
		} else if (rbNota3Cardapio.isSelected()) {
			radio = rbNota3Cardapio.getText();
		} else if (rbNota4Cardapio.isSelected()) {
			radio = rbNota4Cardapio.getText();
		} else if (rbNota5Cardapio.isSelected()) {
			radio = rbNota5Cardapio.getText();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Atenção! Você deve selecionar uma nota para o cardapio.",
					ButtonType.CLOSE);
			alert.show();
		}
		return radio;
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	public String radioPagamento() {
		String radio = null;
		if (rbNota1Pagamento.isSelected()) {
			radio = rbNota1Pagamento.getText();
		} else if (rbNota2Pagamento.isSelected()) {
			radio = rbNota2Pagamento.getText();
		} else if (rbNota3Pagamento.isSelected()) {
			radio = rbNota3Pagamento.getText();
		} else if (rbNota4Pagamento.isSelected()) {
			radio = rbNota4Pagamento.getText();
		} else if (rbNota5Pagamento.isSelected()) {
			radio = rbNota5Pagamento.getText();
		} else {
			Alert alert = new Alert(AlertType.WARNING, "Atenção! Você deve selecionar uma nota para o pagamento.",
					ButtonType.CLOSE);
			alert.show();
		}
		return radio;
	}
}