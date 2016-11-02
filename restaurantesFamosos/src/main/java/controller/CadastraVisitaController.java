package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.Visita;

public class CadastraVisitaController {

	@FXML
	private StackPane stack;

	@FXML
	private BorderPane panelSecundario;

	@FXML
	private DatePicker dpVisita;

	@FXML
	private TextField tfValor;

	@FXML
	private TextField tfNomeRestaurante;

	@FXML
	private TextField tfUsuario;

	@FXML
	private Button btnFinalizar;

	@FXML
	void Salvar(ActionEvent event) {
		criaVisita();
		Alert alert = new Alert(AlertType.CONFIRMATION, "Avaliação efetuada com sucesso!!", ButtonType.CLOSE);
		alert.show();
		AbreTela("TelaVazia.fxml");

	}

	private Visita criaVisita() {
		Visita visita = new Visita();
		visita.setUsuario(tfUsuario.getText());
		visita.setRestaurante(tfNomeRestaurante.getText());
		visita.setData(dpVisita.getValue().toString());
		visita.setValorGasto(Double.valueOf(tfValor.getText()));

		return visita;
	}

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
