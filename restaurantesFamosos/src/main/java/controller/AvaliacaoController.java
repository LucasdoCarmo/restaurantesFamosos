package controller;


import java.util.Collection;


import dao.AvaliacaoDAO;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Avaliacao;

public class AvaliacaoController {

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
	    private ToggleGroup NotaAspectos;

	    @FXML
	    private RadioButton rbPremium;

	    @FXML
	    private ToggleGroup AparenciaExterna;

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
	    private ToggleGroup NotaCardapio;

	    @FXML
	    private RadioButton rbEspecifica;

	    @FXML
	    private ToggleGroup Variedade;

	    @FXML
	    private RadioButton rbNenhuma;

	    @FXML
	    private RadioButton rbSucosRefrigerantesETC;

	    @FXML
	    private RadioButton rbVariedadesDeBebidas;

	    @FXML
	    private RadioButton rbGrandeVariedade;

	    @FXML
	    private RadioButton rbAlgumasAprimoradas;

	    @FXML
	    private RadioButton rbRefrigerantes;

	    @FXML
	    private ToggleGroup Pagamento;

	    @FXML
	    private ToggleGroup QualidadeValorPago;

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
	    private RadioButton rbBarato;

	    @FXML
	    private TextArea taDescricao;

	    @FXML
	    private Button btnVoltar;

	    @FXML
	    private Button btnSalvar;

	    @FXML
	    void Salvar(ActionEvent event) {

	    }

	    @FXML
	    void Voltar(ActionEvent event) {

	    } 
    
    public AvaliacaoController() {
		AvaliacaoDAO = DAOFactory.get().avaliacaoDAO();
		AvaliacaoDAO = DAOFactory.get().avaliacaoDAO();
    
    }
	void onSalvar(ActionEvent event) {
		Avaliacao avaliacao = criaAvaliacao();
		AvaliacaoDAO.salvar(avaliacao);
		taDescricao.setText(avaliacao.getCodigo().toString());
	}

	private Avaliacao criaAvaliacao() {
		String string  = taDescricao.getText();
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setAvaliacaoDescritiva(taDescricao.getText());
		return avaliacao;
     
	}

    
}