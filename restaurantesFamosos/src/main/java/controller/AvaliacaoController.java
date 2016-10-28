package controller;


import java.util.Collection;


import dao.AvaliacaoDAO;
import factory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import model.Avaliacao;

public class AvaliacaoController {

    @FXML
    private ToggleGroup AparenciaInterna;

    @FXML
    private ToggleGroup Variedade;

    @FXML
    private ToggleGroup TempoEntrega;

    @FXML
    private ToggleGroup QualidadeValorPago;

    @FXML
    private ToggleGroup Pagamento;

    @FXML
    private ToggleGroup BebidasAlcoolicas;

    @FXML
    private TextArea taDescricao;

    @FXML
    private Button btnSalvar;

    @FXML
    private ToggleGroup NotaCardapio;

    @FXML
    private ToggleGroup Espera;

    @FXML
    private ToggleGroup Limpeza;

    @FXML
    private Button btnVoltar;

    @FXML
    private ToggleGroup BebidasSemAlcool;

    @FXML
    private ToggleGroup QualidadeAtendimento;

    @FXML
    private ToggleGroup NotaAtendimento;

    @FXML
    private ToggleGroup AparenciaExterna;

    @FXML
    private ToggleGroup NotaAspectos;

    @FXML
    private ToggleGroup OpcoesPagamento;

    @FXML
    void Voltar(ActionEvent event) {

    }

    @FXML
    void Salvar(ActionEvent event) {

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