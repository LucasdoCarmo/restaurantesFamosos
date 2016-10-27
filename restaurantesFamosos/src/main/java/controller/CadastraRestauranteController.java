package controller;

import java.io.IOException;

import componente.ListCellBean;
import componente.StringConverterBean;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private ComboBox<?> cbTema;

    @FXML
    private TextField tfValor;

    @FXML
    private ComboBox<?> cbTipoEstabelecimento;

    @FXML
    private TextField tfRua;

    @FXML
    private TextField tfNome;    
    
    private PaisDAO paisDAO;
    private EstadoDAO estadoDAO;
    private CidadeDAO cidadeDAO;
    private RestauranteDAO restauranteDAO;
    
    public CadastraRestauranteController() {
    	restauranteDAO = DAOFactory.get().restauranteDAO();
	}
    
    
    @FXML
	public void initialize() {
		montaComboPais();
		montaComboEstado();
		montaComboCidade();
	}
    
    
    
    @FXML
    void Voltar(ActionEvent event) {

    }

    @FXML
    void Avaliacao(ActionEvent event) {
    	Restaurante restaurante = criaRestaurante();
    	restauranteDAO.salvar(restaurante);
    	
    	
    	AbreTela("Avaliacao.fxml");
    }
    
    public void AbreTela(String tela) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/telas/" +tela));
		try {
			AnchorPane telaView = (AnchorPane) loader.load();
			panelSecundario.setCenter(telaView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}  
    
    //Metodos nao usados no fx
    
    private void montaComboPais(){
		cbPais.getItems().addAll(paisDAO.todos());
		cbPais.setCellFactory((comboBox) ->{return new ListCellBean<Pais>();});
		cbPais.setConverter(new StringConverterBean<>());
	}
    private void montaComboEstado(){
		cbEstado.getItems().addAll(estadoDAO.todos());
		cbEstado.setCellFactory((comboBox) ->{return new ListCellBean<Estado>();});
		cbEstado.setConverter(new StringConverterBean<>());
	}
    private void montaComboCidade(){
		cbCidade.getItems().addAll(cidadeDAO.todos());
		cbCidade.setCellFactory((comboBox) ->{return new ListCellBean<Cidade>();});
		cbCidade.setConverter(new StringConverterBean<>());
	}
    
    
	private Restaurante criaRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(tfNome.getText());
		restaurante.setCep(Integer.valueOf(tfCep.getText()));
		restaurante.setRua(tfRua.getText());
		restaurante.setNumero(tfNumero.getText());
		restaurante.setPais(cbPais.getValue());
		restaurante.setEstado(cbEstado.getValue());
		restaurante.setCidade(cbCidade.getValue());
		restaurante.setTema(cbTema.getValue());
		restaurante.setTipo(cbTipoEstabelecimento.getValue());
		restaurante.setVisita(dpVisita.getText());
		restaurante.setTelefone(tfTelefone.getText());
		restaurante.setVisita2(tfValor.getText());
		return restaurante;
	}

}
